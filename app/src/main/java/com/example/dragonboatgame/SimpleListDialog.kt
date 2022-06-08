package com.example.dragonboatgame

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView

/**
 *
 * @author : liujianyou
 * @date : 2022/05/30 10:37
 */
class SimpleListDialog(mContext: Context, private val mList: List<String>) :
    Dialog(mContext, R.style.CustomDialog) {

    private var titleStr = "提示"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_simple_list)
        //设置dialog的大小
        val m = window!!.windowManager
        val d = m.defaultDisplay
        val p = window!!.attributes
        p.width = d.width - 160 //设置dialog的宽度为当前手机屏幕的宽度-100
        window!!.attributes = p
        findViewById<TextView>(R.id.dialog_title).text = titleStr

        val recyclerView: RecyclerView = findViewById(R.id.rv_list)
        recyclerView.adapter = SimpleListAdapter()
    }

    private var callback: ((position: Int) -> Unit)? = null

    internal inner class SimpleListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val textView = TextView(parent.context).apply {
                layoutParams=RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,150)
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14f)
                setTextColor(Color.BLACK)
                setPadding(50,0,50,0)
                gravity=Gravity.CENTER_VERTICAL
            }
            val holder = object : RecyclerView.ViewHolder(textView) {}
            textView.setOnClickListener {
                callback?.invoke(holder.layoutPosition)
            }
            return holder
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val textView = holder.itemView as TextView
            textView.text = mList[holder.adapterPosition]
        }

        override fun getItemCount(): Int {
            return mList.size
        }

    }

    fun setOnCallback(callback: (position: Int) -> Unit) {
        this.callback = callback
    }


    fun setTitle(title: String) {
        this.titleStr = title
    }

}