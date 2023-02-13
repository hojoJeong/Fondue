package com.ssafy.fundyou.ui.funding_my_item_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.SingleLiveEvent
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.model.funding.FundingItemInfoModel
import com.ssafy.fundyou.domain.usecase.funding.GetFundingItemUseCase
import com.ssafy.fundyou.domain.usecase.funding.GetFundingParticipateMessageUseCase
import com.ssafy.fundyou.ui.funding_my_item_detail.model.MyFundingItemDetailUiModel
import com.ssafy.fundyou.ui.funding_my_item_detail.model.MyFundingParticipateMessageModel
import com.ssafy.fundyou.ui.funding_my_item_detail.model.toFundingParticipateUserModel
import com.ssafy.fundyou.ui.funding_my_item_detail.model.toMyFundingItemDetailUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyFundingItemDetailViewModel @Inject constructor(
    private val getFundingItemUseCase: GetFundingItemUseCase,
    private val getFundingParticipateMessageUseCase: GetFundingParticipateMessageUseCase
) : ViewModel() {
    private val _fundingItem = SingleLiveEvent<ViewState<MyFundingItemDetailUiModel>>()
    val fundingItem: LiveData<ViewState<MyFundingItemDetailUiModel>> get() = _fundingItem

    private val _fundingParticipateMessageList =
        SingleLiveEvent<ViewState<List<MyFundingParticipateMessageModel>>>()
    val fundingParticipateMessageList: LiveData<ViewState<List<MyFundingParticipateMessageModel>>> get() = _fundingParticipateMessageList

    fun getFundingItemInfo(fundingItemId: Long) = viewModelScope.launch {
        _fundingItem.postValue(ViewState.Loading())
        try {
            val response = getFundingItemUseCase(fundingItemId).toMyFundingItemDetailUiModel()
            _fundingItem.postValue(ViewState.Success(response))
        } catch (e: Exception) {
            _fundingItem.postValue(ViewState.Error(e.message))
        }
    }

    fun getFundingParticipateMessageList(fundingItemId: Long) = viewModelScope.launch {
        _fundingParticipateMessageList.postValue(ViewState.Loading())
        try {
            val response =
                getFundingParticipateMessageUseCase(fundingItemId).map { it.toFundingParticipateUserModel() }
            _fundingParticipateMessageList.postValue(ViewState.Success(response))
        } catch (e: Exception) {
            _fundingParticipateMessageList.postValue(ViewState.Error(e.message))
        }
    }
}