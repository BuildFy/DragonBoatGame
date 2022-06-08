package com.example.dragonboatgame

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 *
 * @author : liujianyou
 * @date : 2022/06/06 09:06
 */
class FortuneListActivity : AppCompatActivity() {

    private val allImgList by lazy { ZongKaHelper.getAll(this) }

    private val gridLinearLayoutManager = GridLayoutManager(this, 3)

    private val bannerRecyclerView: RecyclerView by lazy { findViewById(R.id.banner) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fortune)
        findViewById<View>(R.id.img_back).setOnClickListener { finish() }

        bannerRecyclerView.layoutManager = gridLinearLayoutManager
        bannerRecyclerView.setHasFixedSize(true)
        bannerRecyclerView.adapter = FortuneAdapter(allImgList)

    }


}
