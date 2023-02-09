package com.ssafy.fundyou.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.usecase.user.GetUserInfoUserCase
import com.ssafy.fundyou.ui.mypage.model.UserInfoModel
import com.ssafy.fundyou.ui.mypage.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(private val getUserInfoUserCase: GetUserInfoUserCase) :
    ViewModel() {
    private val _userInfo = MutableLiveData<ViewState<UserInfoModel>>()
    val userInfo: LiveData<ViewState<UserInfoModel>>
        get() = _userInfo

    fun getUserInfo() = viewModelScope.launch {
        _userInfo.value = ViewState.Loading()
        try {
            val response = getUserInfoUserCase()
            _userInfo.value = ViewState.Success(response.toUiModel())
        } catch (e: Exception) {
            _userInfo.value = ViewState.Error(e.message)
        }
    }
}