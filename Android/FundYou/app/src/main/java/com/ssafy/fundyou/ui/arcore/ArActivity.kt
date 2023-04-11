package com.ssafy.fundyou.ui.arcore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssafy.fundyou.databinding.ActivityArBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArActivity : AppCompatActivity() {
    private lateinit var binding : ActivityArBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityArBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}