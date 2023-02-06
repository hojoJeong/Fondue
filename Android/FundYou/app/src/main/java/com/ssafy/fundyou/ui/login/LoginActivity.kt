package com.ssafy.fundyou.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.showAlignTop
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.ActivityLoginBinding
import com.ssafy.fundyou.ui.MainActivity
import com.ssafy.fundyou.ui.login.banner.adapter.LoginBannerAdapter
import com.ssafy.fundyou.ui.login.banner.model.LoginBannerModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()
    private val kakaoLoginCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e(TAG, "카카오계정으로 로그인 실패", error)
        } else if (token != null) {
            loginViewModel.getJWTByKakao(token.accessToken)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLoginBanner()
        initLoginViewModel()
        addKakaoLoginEvent()
    }

    private fun initLoginViewModel() {
        loginViewModel.jwt.observe(this) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initLoginViewModel: get token...")
                }
                is ViewState.Success -> {
                    Log.d(TAG, "initLoginViewModel: success ${response.value}")
                    startMainActivity()
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initLoginViewModel: token error ${response.message}")
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

    private fun initLoginBanner() {
        val bannerAdapter = LoginBannerAdapter(this)
        bannerAdapter.addAllBannerList(initLoginBannerList())

        with(binding) {
            vpLoginBanner.adapter = bannerAdapter
            ciLoginBannerIndicator.setViewPager(vpLoginBanner)
            btnLoginKakao.showAlignTop(makeBalloon())
        }
    }

    private fun addKakaoLoginEvent() {
        with(binding) {
            btnLoginKakao.setOnClickListener {
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(this@LoginActivity)) {
                    UserApiClient.instance.loginWithKakaoTalk(this@LoginActivity) { token, error ->
                        if (error != null) {
                            if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                                return@loginWithKakaoTalk
                            }
                            UserApiClient.instance.loginWithKakaoAccount(
                                this@LoginActivity,
                                callback = kakaoLoginCallback
                            )
                        } else if (token != null) {
                            Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                        }
                    }
                } else {
                    UserApiClient.instance.loginWithKakaoAccount(
                        this@LoginActivity,
                        callback = kakaoLoginCallback
                    )
                }
            }
        }
    }

    private fun makeBalloon(): Balloon {
        val popUpMessage = Balloon.Builder(this)
            .setWidth(BalloonSizeSpec.WRAP)
            .setHeight(BalloonSizeSpec.WRAP)
            .setText(resources.getString(R.string.content_login_balloon))
            .setTextColorResource(R.color.white)
            .setTextTypeface(ResourcesCompat.getFont(this, R.font.notosanskr_medium)!!)
            .setTextSize(13f)
            .setIconHeight(20)
            .setMarginBottom(6)
            .setIconWidth(20)
            .setIconDrawableResource(R.drawable.ic_launcher_background)
            .setArrowSize(12)
            .setArrowPosition(0.5f)
            .setPaddingTop(6)
            .setPaddingLeft(10)
            .setPaddingRight(10)
            .setPaddingBottom(6)
            .setCornerRadius(10f)
            .setBackgroundColorResource(R.color.grey)
            .setDismissWhenClicked(false)
            .setDismissWhenOverlayClicked(false)
            .setDismissWhenTouchOutside(false)

        return popUpMessage.build()
    }

    private fun initLoginBannerList(): List<LoginBannerModel> {
        val list = mutableListOf<LoginBannerModel>()
        list.add(
            LoginBannerModel(
                R.drawable.bg_onboarding_1,
                getString(R.string.content_login_banner_1)
            )
        )
        list.add(
            LoginBannerModel(
                R.drawable.bg_onboarding_2,
                getString(R.string.content_login_banner_2)
            )
        )
        list.add(
            LoginBannerModel(
                R.drawable.bg_onboarding_3,
                getString(R.string.content_login_banner_3)
            )
        )

        return list
    }

    companion object {
        private const val TAG = "LoginActivity..."
    }
}