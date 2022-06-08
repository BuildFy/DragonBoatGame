package com.example.dragonboatgame

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 *
 * @author : liujianyou
 * @date : 2022/05/30 09:28
 */
class LotteryActivity : AppCompatActivity() {
    private val lucky_panel: LuckyMonkeyPanelView by lazy { findViewById(R.id.lucky_panel) }
    private val btn_action: Button by lazy { findViewById(R.id.btn_action) }
    private val tv_integral: TextView by lazy { findViewById(R.id.tv_integral) }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottery)

        btn_action.setOnClickListener {
            if (SpUtil.getInt(this) <= 0) {
                Toast.makeText(this, "积分不足，请先进行游戏获取积分吧", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            tv_integral.text = "积分：${SpUtil.minusInt(this, 2)}"
            if (!lucky_panel.isGameRunning) {
                lucky_panel.startGame()
                val stayIndex = (0..7).random()
                Log.e("LuckyMonkeyPanelView", "====stay===$stayIndex")
                lucky_panel.postOnAnimationDelayed({
                    lucky_panel.tryToStop(stayIndex)
                    Toast.makeText(this, "抽奖成功，前往奖品列表界面进行查看", Toast.LENGTH_SHORT).show()
                }, 1500)
                ZongKaHelper.luckyDraw(this, stayIndex)
            }
        }
        tv_integral.text = "积分：${SpUtil.getInt(this)}"
        findViewById<TextView>(R.id.tv_fortune).setOnClickListener {
            startActivity(Intent(this, FortuneListActivity::class.java))
        }
        findViewById<View>(R.id.img_back).setOnClickListener {
            finish()
        }
    }
}