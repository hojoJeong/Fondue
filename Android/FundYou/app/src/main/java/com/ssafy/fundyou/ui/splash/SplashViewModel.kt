package com.ssafy.fundyou.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.ErrorMessage.AUTHORIZATION_ERROR
import com.ssafy.fundyou.common.ErrorMessage.NO_TOKEN
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.model.auth.JWTAuthModel
import com.ssafy.fundyou.domain.usecase.auth.GetAccessTokenUseCase
import com.ssafy.fundyou.domain.usecase.auth.GetJWTByRefreshTokenUseCase
import com.ssafy.fundyou.domain.usecase.auth.GetRefreshTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/** 무조건 앱을 킬때마다 토큰 갱신 */
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getJWTByRefreshTokenUseCase: GetJWTByRefreshTokenUseCase

) : ViewModel() {
    private val _accessToken = MutableLiveData<ViewState<String>>()
    val accessToken: LiveData<ViewState<String>> get() = _accessToken

    private val _refreshJWT = MutableLiveData<ViewState<JWTAuthModel>>()
    val refreshJWT: LiveData<ViewState<JWTAuthModel>> get() = _refreshJWT

    fun getLocalAccessToken() = viewModelScope.launch {
        _accessToken.value = ViewState.Loading()
        try {
            val response = getAccessTokenUseCase()
            if (response.isEmpty()) _accessToken.value = ViewState.Error(NO_TOKEN)
            else _accessToken.value = ViewState.Success(response)
        } catch (e: Exception) {
            _accessToken.value = ViewState.Error("ERROR")
        }
    }

    fun getJWTByRefreshToken() = viewModelScope.launch {
        _refreshJWT.value = ViewState.Loading()
        try {
            val response = getJWTByRefreshTokenUseCase()
            if (response.statusMessage == "탈퇴된 계정입니다.") _refreshJWT.value = ViewState.Error(response.statusMessage)
            else _refreshJWT.value = ViewState.Success(response)
        } catch (e: Exception) {
            _refreshJWT.value = ViewState.Error("ERROR")
        }
    }
}