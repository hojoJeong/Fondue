package com.ssafy.fundyou.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.model.auth.JWTAuthModel
import com.ssafy.fundyou.domain.usecase.auth.GetKakaoAuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val getKakaoAuthUseCase: GetKakaoAuthUseCase) :
    ViewModel() {
    private val _jwtByKakao = MutableLiveData<ViewState<JWTAuthModel>>()
    val jwt: LiveData<ViewState<JWTAuthModel>> get() = _jwtByKakao

    private val _jwtByRefreshToken = MutableLiveData<ViewState<JWTAuthModel>>()
    val jwtByRefreshToken: LiveData<ViewState<JWTAuthModel>> get() = _jwtByRefreshToken

    fun getJWTByKakao(accessToken: String) = viewModelScope.launch {
        _jwtByKakao.value = ViewState.Loading()
        try {
            val response = getKakaoAuthUseCase(accessToken)
            if (response.accessToken.isEmpty()) _jwtByKakao.value = ViewState.Error("Empty Token")
            else _jwtByKakao.value = ViewState.Success(response)
        } catch (e: Exception) {
            _jwtByKakao.value = ViewState.Error(e.message)
        }
    }

    fun getJWTByRefreshToken(accessToken: String) = viewModelScope.launch {
        _jwtByRefreshToken.value = ViewState.Loading()
        try {
            val response = getKakaoAuthUseCase(accessToken)
            if (response.accessToken.isEmpty()) _jwtByKakao.value = ViewState.Error("Empty Token")
            else _jwtByRefreshToken.value = ViewState.Success(response)
        } catch (e: Exception) {
            _jwtByRefreshToken.value = ViewState.Error(e.message)
        }
    }
}