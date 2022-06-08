package com.example.dragonboatgame

import android.content.Context


/**
 *
 * @author : liujianyou
 * @date : 2022/06/06 17:06
 */
object SpUtil {


    fun putInt(mContext: Context, value: Int) {
        val sharedPreferences = mContext.getSharedPreferences("sp_name", Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        edit.putInt("valueKey", value)
        edit.apply()
    }

    fun getInt(mContext: Context): Int {
        val sharedPreferences = mContext.getSharedPreferences("sp_name", Context.MODE_PRIVATE)
        return sharedPreferences.getInt("valueKey", 0)
    }

    fun minusInt(mContext: Context, value: Int): Int {
        var mValue = getInt(mContext)
        mValue -= value
        if (mValue < 0) {
            mValue = 0
        }
        putInt(mContext, mValue)
        return mValue
    }

    fun putPrize(mContext: Context, key: String) {
        val prize = getPrize(mContext, key)+1
        val sharedPreferences = mContext.getSharedPreferences("sp_name", Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        edit.putInt("key$key", prize)
        edit.apply()
    }

    fun getPrize(mContext: Context, key: String): Int {
        val sharedPreferences = mContext.getSharedPreferences("sp_name", Context.MODE_PRIVATE)
        return sharedPreferences.getInt("key$key", 0)
    }


}