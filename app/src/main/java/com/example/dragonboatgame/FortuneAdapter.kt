package com.example.dragonboatgame

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import java.io.IOException

/**
 *
 * @author : liujianyou
 * @date : 2022/06/06 10:37
 */
class FortuneAdapter(private val resList: List<DataBean>) :
    RecyclerView.Adapter<FortuneAdapter.ViewHolder>() {

    private val mWidth by lazy {
        val density = Resources.getSystem().displayMetrics.widthPixels
        (density - 90) / 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.item_fortune, null)
        val textView = inflate.apply {
            layoutParams = RecyclerView.LayoutParams(
                mWidth,
                mWidth
            )
            setPadding(15)
        }
        return ViewHolder(textView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val loadImg = loadImg(position, holder.itemView.context)
        if (!resList[position].obtain) {
            holder.viewObtain.visibility = View.VISIBLE
        } else {
            holder.viewObtain.visibility = View.GONE
        }
        if (resList[position].num > 0) {
            holder.tvNum.visibility = View.VISIBLE
            holder.tvNum.text = "${resList[position].num}"
        } else {
            holder.tvNum.visibility = View.GONE
        }
        //图片加载自己实现
        Glide.with(holder.itemView)
            .load(loadImg)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = resList.size

    private fun loadImg(position: Int, context: Context): Bitmap {
        var bitmap: Bitmap? = null
        val assetManager = context.assets
        try {
            val inputStream =
                assetManager.open("resImg/${position + 1}.png") //filename是assets目录下的图片名
            bitmap = BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        if (bitmap == null) {
            bitmap = ContextCompat.getDrawable(context, R.mipmap.ic_launcher)!!.toBitmap(400, 400)
        }
        return bitmap

    }

    open inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val imageView: ImageView by lazy { mView.findViewById(R.id.iv_img) }
        val viewObtain: View by lazy { mView.findViewById(R.id.v_obtain) }
        val tvNum: TextView by lazy { mView.findViewById(R.id.tv_num) }
    }
}