package com.ssafy.fundyou.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.usecase.user.GetUserInfoUserCase
import com.ssafy.fundyou.domain.usecase.user.LogoutUseCase
import com.ssafy.fundyou.domain.usecase.user.WithdrawalMembershipUseCase
import com.ssafy.fundyou.ui.mypage.model.UserInfoModel
import com.ssafy.fundyou.ui.mypage.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val getUserInfoUserCase: GetUserInfoUserCase,
    private val withdrawalMembershipUseCase: WithdrawalMembershipUseCase,
    private val logoutUseCase: LogoutUseCase
) :
    ViewModel() {
    private val _userInfo = MutableLiveData<ViewState<UserInfoModel>>()
    val userInfo: LiveData<ViewState<UserInfoModel>>
        get() = _userInfo

    private val _resultWithdrawal = MutableLiveData<ViewState<Int>>()
    val resultWithdrawal: LiveData<ViewState<Int>>
        get() = _resultWithdrawal
    
    private val _resultLogout = MutableLiveData<ViewState<Boolean>>()
    val resultLogout: LiveData<ViewState<Boolean>>
        get() = _resultLogout
    
    fun getUserInfo() = viewModelScope.launch {
        _userInfo.value = ViewState.Loading()
        try {
            val response = getUserInfoUserCase()
            _userInfo.value = ViewState.Success(response.toUiModel())
        } catch (e: Exception) {
            _userInfo.value = ViewState.Error(e.message)
        }
    }

    fun withdrawalMembership() = viewModelScope.launch {
        _resultWithdrawal.value = ViewState.Loading()
        try {
            val response = withdrawalMembershipUseCase()
            _resultWithdrawal.value = ViewState.Success(response)
        } catch (e: Exception) {
            _resultWithdrawal.value = ViewState.Error(e.message)
        }
    }

    fun clearAuthPreference(){
        _resultLogout.value = ViewState.Loading()
        try {
            val response = logoutUseCase()
            _resultLogout.value = ViewState.Success(response)
        } catch (e: Exception){
            _resultLogout.value = ViewState.Error(e.message)
        }
    }
}