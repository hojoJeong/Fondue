package com.ssafy.fundyou.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ActivityBridgeBinding
import com.ssafy.fundyou.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}