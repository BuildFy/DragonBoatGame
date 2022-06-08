package com.example.dragonboatgame

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.AttributeSet
import android.util.TypedValue.COMPLEX_UNIT_DIP
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView

/**
 *
 * @author : liujianyou
 * @date : 2022/05/30 09:51
 */
class GameTipView(mContext: Context, attributeSet: AttributeSet?) :
    AppCompatTextView(mContext, attributeSet) {

    init {
        initView()
    }

    private var callback: (() -> Unit)? = null

    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1 -> {
                    postOnAnimation {
                        setTextSize(COMPLEX_UNIT_DIP, 60f)
                        text = "3"
                        sendEmptyMessageDelayed(2, 1000)
                    }
                }
                2 -> {
                    postOnAnimation {
                        setTextSize(COMPLEX_UNIT_DIP, 60f)
                        text = "2"
                        sendEmptyMessageDelayed(3, 1000)
                    }
                }
                3 -> {
                    postOnAnimation {
                        setTextSize(COMPLEX_UNIT_DIP, 60f)
                        text = "1"
                        sendEmptyMessageDelayed(4, 1000)
                    }
                }
                4 -> {
                    postOnAnimation {
                        visibility = GONE
                        callback?.invoke()
                    }
                }
                else -> {}
            }
        }
    }

    private fun initView() {
        setTextSize(COMPLEX_UNIT_DIP, 15f)
        gravity = Gravity.CENTER
        text = "轻触屏幕开始游戏！"
        setOnClickListener {
            mHandler.sendEmptyMessage(1)
        }
    }

    fun resetAndStart() {
        visibility = VISIBLE
        mHandler.removeCallbacksAndMessages(null)
        textSize = 15f
        text = "轻触屏幕开始游戏！"
    }

    fun setOnConfirmListener(callback: () -> Unit) {
        this.callback = callback
    }

}