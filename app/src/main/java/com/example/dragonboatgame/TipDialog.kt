package com.example.dragonboatgame

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView

/**
 *
 * @author : liujianyou
 * @date : 2022/05/30 10:32
 */
class TipDialog(mContext: Context) : Dialog(mContext, R.style.CustomDialog) {

    private var titleStr = "提示"
    private var contentStr = ""
    private var leftBtn = "取消"
    private var rightBtn = "确认"
    private var leftVisible = false
    private var rightVisible = false
    private var leftCallback: (() -> Unit)? = null
    private var rightCallback: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_tip)
        //设置dialog的大小
        val m = window!!.windowManager
        val d = m.defaultDisplay
        val p = window!!.attributes
        p.width = d.width - 160 //设置dialog的宽度为当前手机屏幕的宽度-100
        window!!.attributes = p
        findViewById<TextView>(R.id.dialog_title).text = titleStr
        findViewById<TextView>(R.id.dialog_content).text = contentStr
        val leftBt = findViewById<TextView>(R.id.left_btn)
        if (leftVisible) {
            leftBt.visibility = View.GONE
        } else {
            leftBt.apply {
                text = leftBtn
                setOnClickListener {
                    leftCallback?.invoke()
                }
            }
        }

        val rightBt = findViewById<TextView>(R.id.right_btn)
        if (rightVisible) {
            rightBt.visibility = View.GONE
        } else {
            rightBt.apply {
                text = rightBtn
                setOnClickListener {
                    rightCallback?.invoke()
                }
            }
        }


    }

    fun setTitle(title: String) {
        this.titleStr = title
    }

    fun setLeftBtn(leftStr: String = "取消", callback: (() -> Unit)? = null) {
        this.leftBtn = leftStr
        this.leftCallback = callback
    }

    fun setRightBtn(rightStr: String = "确认", callback: (() -> Unit)? = null) {
        this.rightBtn = rightStr
        this.rightCallback = callback
    }

    fun setLeftVisible() {
        leftVisible = true
    }

    fun setRightVisible() {
        rightVisible = true
    }

    fun setContentTxt(contentStr: String) {
        this.contentStr = contentStr
    }


}