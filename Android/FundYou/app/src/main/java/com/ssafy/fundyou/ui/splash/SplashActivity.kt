package com.ssafy.fundyou.ui.splash

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.fundyou.common.ErrorMessage.AUTHORIZATION_ERROR
import com.ssafy.fundyou.common.ErrorMessage.NO_TOKEN
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.ActivitySplashBinding
import com.ssafy.fundyou.ui.MainActivity
import com.ssafy.fundyou.ui.login.LoginActivity
import com.ssafy.fundyou.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSplashViewModel()
        tokenValidation()
    }

    private fun tokenValidation() {
        Handler(mainLooper).postDelayed({
            splashViewModel.getLocalAccessToken()
        }, 1000)
    }

    private fun initSplashViewModel() {
        splashViewModel.accessToken.observe(this) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    splashViewModel.getJWTByRefreshToken()
                }
                is ViewState.Error -> {
                    if (response.message == NO_TOKEN) {
                        Log.d(TAG, "initSplashViewModel: no token")
                        startLoginActivity()
                    }
                }
            }
        }

        splashViewModel.refreshJWT.observe(this) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    startMainActivity()
                }
                is ViewState.Error -> {
                    startLoginActivity()
                }
            }
        }
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

    companion object {
        private const val TAG = "SplashActivity..."
    }
}