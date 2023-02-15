package com.ssafy.fundyou.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.usecase.fcm.GetNotificationSettingStatusUseCase
import com.ssafy.fundyou.domain.usecase.fcm.UpdateNotificationSettingStatusUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FcmViewModel @Inject constructor(
    private val getNotificationSettingStatusUseCase: GetNotificationSettingStatusUseCase,
    private val updateNotificationSettingStatusUseCase: UpdateNotificationSettingStatusUseCase,
) : ViewModel() {

    private val _resultAddFcmToken = MutableLiveData<ViewState<String>>()
    val resultAddFcmToken: LiveData<ViewState<String>>
        get() = _resultAddFcmToken

    private val _notiStatus = MutableLiveData<ViewState<Int>>()
    val notiStatus: LiveData<ViewState<Int>>
        get() = _notiStatus

    private val _resultUpdateNotiSetting = MutableLiveData<ViewState<String>>()
    val resultUpdateNotiSetting: LiveData<ViewState<String>>
        get() = _resultUpdateNotiSetting

    fun getNotificationStatus() = viewModelScope.launch {
        _notiStatus.value = ViewState.Loading()
        try {
            val response = getNotificationSettingStatusUseCase()
            _notiStatus.value = ViewState.Success(response)
        }catch (e: Exception){
            _notiStatus.value = ViewState.Error(e.message)
        }
    }

    fun updateNotificationStatus() = viewModelScope.launch {
        _resultUpdateNotiSetting.value = ViewState.Loading()
        try {
            updateNotificationSettingStatusUseCase()
        } catch (e: Exception){
            _resultUpdateNotiSetting.value = ViewState.Error(e.message)
        }
    }
}