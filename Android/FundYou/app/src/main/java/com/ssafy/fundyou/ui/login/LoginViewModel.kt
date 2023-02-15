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

    fun getJWTByKakao(accessToken: String) = viewModelScope.launch {
        _jwtByKakao.value = ViewState.Loading()
        try {
            val response = getKakaoAuthUseCase(accessToken)
            if (response.statusMessage == "탈퇴된 계정입니다.") _jwtByKakao.value = ViewState.Error(response.statusMessage)
            else _jwtByKakao.value = ViewState.Success(response)
        } catch (e: Exception) {
            _jwtByKakao.value = ViewState.Error(e.message)
        }
    }
}