package com.ssafy.fundyou.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.usecase.fcm.GetNotificationSettingStatusUseCase
import com.ssafy.fundyou.domain.usecase.fcm.UpdateNotificationSettingStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FcmViewModel @Inject constructor(
    private val getNotificationSettingStatusUseCase: GetNotificationSettingStatusUseCase,
    private val updateNotificationSettingStatusUseCase: UpdateNotificationSettingStatusUseCase,
) : ViewModel() {
    private val _notiStatus = MutableLiveData<ViewState<Int>>()
    val notiStatus: LiveData<ViewState<Int>>
        get() = _notiStatus

    private val _resultUpdateNotiSetting = MutableLiveData<ViewState<Int>>()
    val resultUpdateNotiSetting: LiveData<ViewState<Int>>
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
            _resultUpdateNotiSetting.value = ViewState.Success(0)
        } catch (e: Exception){
            _resultUpdateNotiSetting.value = ViewState.Error(e.message)
        }
    }
}