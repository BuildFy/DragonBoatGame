package com.example.dragonboatgame

import android.content.Context

/**
 *粽卡管理
 *
 * @author : liujianyou
 * @date : 2022/06/08 09:19
 */
object ZongKaHelper {

    fun getAll(mContext: Context): List<DataBean> {
        val list = mutableListOf<DataBean>()
        for (i in 1 until 68) {
            val key = "$i"
            val prize = SpUtil.getPrize(mContext, key)
            val dataBean = DataBean(key = key, prize > 0, prize)
            list.add(dataBean)
        }
        return list
    }

    fun luckyDraw(mContext: Context, value: Int): Int {
        val i = value + 1
        val intRange = (1..67).random()
        return if (intRange > i) {
            SpUtil.putPrize(mContext, "$i")
            i
        } else {
            SpUtil.putPrize(mContext, "$intRange")
            intRange
        }
    }


}