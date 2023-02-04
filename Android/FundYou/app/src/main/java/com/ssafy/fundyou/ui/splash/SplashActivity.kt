package com.ssafy.fundyou.ui.splash

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.fundyou.databinding.ActivitySplashBinding
import com.ssafy.fundyou.ui.MainActivity
import com.ssafy.fundyou.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tokenValidation()
    }

    private fun tokenValidation() {
        val handler = Handler(mainLooper)
        // 현재 2초뒤 로그인 화면으로 넘어감, 토큰 검증 API 나오면 수정
        handler.postDelayed({
            startLoginActivity()  //테스트로 인한 임시 변경
//            startMainActivity()
        }, 1000)
    }

    private fun startMainActivity() {
        // CLEAR_TASK or NEW_TASK로 스택에 있는 액티비티 전부 제거
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }

    private fun startLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }
}