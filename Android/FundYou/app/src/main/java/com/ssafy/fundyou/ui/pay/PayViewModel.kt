package com.ssafy.fundyou.ui.pay

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.SingleLiveEvent
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.usecase.funding.GetFundingItemUseCase
import com.ssafy.fundyou.domain.usecase.pay.AttendFundingItemUseCase
import com.ssafy.fundyou.domain.usecase.user.GetUserInfoUserCase
import com.ssafy.fundyou.ui.pay.model.FundingPayItemUiModel
import com.ssafy.fundyou.ui.pay.model.PayUserUiModel
import com.ssafy.fundyou.ui.pay.model.toFundingPayItemUiModel
import com.ssafy.fundyou.ui.pay.model.toPayUserUiModel
import com.ssafy.fundyou.ui.pay_result.model.PayResultUiModel
import com.ssafy.fundyou.ui.pay_result.model.toPayResultUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PayViewModel @Inject constructor(
    private val getFundingItemUseCase: GetFundingItemUseCase,
    private val getUserInfoUserCase: GetUserInfoUserCase,
    private val attendFundingItemUseCase: AttendFundingItemUseCase
) : ViewModel() {
    private val _fundingItem = SingleLiveEvent<ViewState<FundingPayItemUiModel>>()
    val fundingItem: LiveData<ViewState<FundingPayItemUiModel>> get() = _fundingItem

    private val _userInfo = SingleLiveEvent<ViewState<PayUserUiModel>>()
    val userInfo: LiveData<ViewState<PayUserUiModel>> get() = _userInfo

    private val _attendFundingItem = SingleLiveEvent<ViewState<PayResultUiModel>>()
    val attendFundingItem: LiveData<ViewState<PayResultUiModel>> get() = _attendFundingItem

    fun attendFundingItem(fundingItemId: Long, message: String, point: Int) =
        viewModelScope.launch {
            _attendFundingItem.postValue(ViewState.Loading())
            try {
                val response =
                    attendFundingItemUseCase(fundingItemId, message, point).toPayResultUiModel()
                _attendFundingItem.postValue(ViewState.Success(response))
            } catch (e: Exception) {
                _attendFundingItem.postValue(ViewState.Error(e.message))
            }
        }

    fun getFundingItem(fundingItemId: Long) = viewModelScope.launch {
        _fundingItem.postValue(ViewState.Loading())
        try {
            val response = getFundingItemUseCase(fundingItemId).toFundingPayItemUiModel()
            _fundingItem.postValue(ViewState.Success(response))
        } catch (e: Exception) {
            _fundingItem.postValue(ViewState.Error(e.message))
        }
    }

    fun getPayUserInfo() = viewModelScope.launch {
        _userInfo.postValue(ViewState.Loading())
        try {
            val response = getUserInfoUserCase().toPayUserUiModel()
            _userInfo.postValue(ViewState.Success(response))
        } catch (e: Exception) {
            _userInfo.postValue(ViewState.Error(e.message))
        }
    }
}