package com.ssafy.fundyou.ui.bridge

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.fundyou.databinding.ActivityBridgeBinding
import com.ssafy.fundyou.ui.bridge.adapter.BridgeAdapter
import com.ssafy.fundyou.ui.keyhash.KeyHashActivity
import com.ssafy.fundyou.ui.splash.SplashActivity
import com.ssafy.fundyou.ui.token.DeleteTokenActivity

class BridgeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBridgeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBridgeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initList()
    }

    private fun initList(){
        val adapter = BridgeAdapter(this)
        val layoutList = arrayListOf<Class<out Activity>>(
            SplashActivity::class.java,
            DeleteTokenActivity::class.java,
            KeyHashActivity::class.java
        )
        adapter.addItems(layoutList)
        binding.rvBridge.apply {
            this.layoutManager = LinearLayoutManager(this@BridgeActivity, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
        }
    }
}