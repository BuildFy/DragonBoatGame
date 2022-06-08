package com.example.dragonboatgame

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap

/**
 *
 * @author : liujianyou
 * @date : 2022/05/26 09:37
 */
@SuppressLint("UseCompatLoadingForDrawables")
class DragonBoatView(private val mContext: Context, private val attributeSet: AttributeSet?) :
    View(mContext, attributeSet) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val mBitmap by lazy {
        ContextCompat.getDrawable(context, R.mipmap.longzhou)?.toBitmap(120, 150)
    }

    private var tipText = ""

    private val mTipDialog: TipDialog by lazy {
        TipDialog(mContext).apply {
            setContentTxt(tipText)
            setLeftVisible()
            setRightBtn {
                mTipDialog.dismiss()
            }
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }
    }

    private val mRectF = RectF()

    private val parseColor = Color.parseColor("#FFBB86FC")


    private var userCircleY = 0f
        set(value) {
            field = value
            invalidate()
        }

    private var lastTime = -1
    private var start = 101
    private var value = 20
    private var mCircleY = 0f
        set(value) {
            field = value
            action()
        }

    init {

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPaint.color = parseColor
        canvas?.drawRoundRect(mRectF, 20f, 20f, mPaint)
        mBitmap?.let {
            canvas?.drawBitmap(it, ((width / 4) - (it.width / 2)).toFloat(), mCircleY, mPaint)
        }
        mBitmap?.copy(Bitmap.Config.ARGB_8888, false)?.let {
            canvas?.drawBitmap(
                it,
                ((width - (width / 4).toFloat()) - (it.width / 2)), userCircleY, mPaint
            )
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mCircleY = height.toFloat()
        userCircleY = height.toFloat()
        val mWidth = (width / 2).toFloat()

        mRectF.top = 0f
        mRectF.bottom = height.toFloat()
        mRectF.left = mWidth - 10
        mRectF.right = mWidth + 10
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.actionMasked == MotionEvent.ACTION_DOWN) {
            start()
            if (userCircleY >= 0f) {
                calculateSpeed()
            } else {
                if (mCircleY > 0) {
                    tipText = "玩家赢了"
                    SpUtil.putInt(mContext, 2)
                    mTipDialog.show()
                    reset()
                }
            }
        }
        return super.onTouchEvent(event)
    }


    fun start() {
        if (start == 102)
            return
        start = 102
        action()
    }

    private fun action() {
        if (start == 101)
            return
        if (mCircleY >= 0f) {
            postOnAnimation {
                mCircleY -= (0..3).random()
                invalidate()
            }
        } else {
            if (userCircleY > 0) {
                tipText = "系统赢了"
                mTipDialog.show()
                reset()
            }
        }
    }

    fun reset() {
        start = 101
        mCircleY = height.toFloat()
        userCircleY = height.toFloat()
    }

    fun pause() {
        start = 101
    }


    private fun calculateSpeed() {
        if ((System.currentTimeMillis() - lastTime) < 100) {
            value += 1
        } else
            value = 20
        userCircleY -= value
    }

}