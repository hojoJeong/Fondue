package com.ssafy.fundyou.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.model.GoogleAuthModel
import com.ssafy.fundyou.domain.usecase.auth.GetGoogleAuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val getGoogleAuthUseCases: GetGoogleAuthUseCase) :
    ViewModel() {
    private val _googleAuthToken = MutableLiveData<ViewState<GoogleAuthModel>>()
    val googleAuthToken: LiveData<ViewState<GoogleAuthModel>> get() = _googleAuthToken

    fun getGoogleAuthToken(authCode : String, clientId : String, clientSecretId : String) = viewModelScope.launch {
        _googleAuthToken.value = ViewState.Loading()
        try {
            val response = getGoogleAuthUseCases(authCode, clientId, clientSecretId)
            _googleAuthToken.value = ViewState.Success(response)
        }catch (e : Exception){
            _googleAuthToken.value = ViewState.Error(e.message)
        }
    }
}