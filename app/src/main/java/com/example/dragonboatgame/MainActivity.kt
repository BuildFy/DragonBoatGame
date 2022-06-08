package com.example.dragonboatgame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {


    private val mGameTipView: GameTipView by lazy { findViewById(R.id.game_tip) }
    private val mGameDragonBoatView: DragonBoatView by lazy { findViewById(R.id.game_dragon_boat) }
    private val mBtConfig: AppCompatButton by lazy { findViewById(R.id.bt_config) }
    private var needTip = false

    private val mConfigDialog: SimpleListDialog by lazy {
        SimpleListDialog(this, mutableListOf<String>().apply {
            add("重玩")
            add("抽奖得粽卡")
            add("继续游戏")
        }).apply {
            setTitle("选项")
            setOnCallback {
                when (it) {
                    0 -> {
                        dismiss()
                        mGameDragonBoatView.reset()
                        mGameTipView.resetAndStart()
                    }
                    1 -> {
                        startActivity(Intent(this@MainActivity, LotteryActivity::class.java))
                        dismiss()
                    }
                    2 -> {
                        needTip = true
                        dismiss()
                    }
                    else -> {}
                }
            }
        }
    }

    private val mTipDialog: TipDialog by lazy {
        TipDialog(this).apply {
            setContentTxt("是否继续游戏？")
            setLeftBtn {
                mTipDialog.dismiss()
            }
            setRightBtn {
                mGameDragonBoatView.start()
                mTipDialog.dismiss()
            }
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mGameTipView.setOnConfirmListener {
            mGameDragonBoatView.start()
        }
        mBtConfig.setOnClickListener {
            mGameDragonBoatView.pause()
            mConfigDialog.show()
        }
        mConfigDialog.setOnDismissListener {
            if (needTip) {
                needTip = false
                mTipDialog.show()
            }
        }


    }

}

