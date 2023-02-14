package com.ssafy.fundyou.ui.funding_participate

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.SingleLiveEvent
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.usecase.funding.GetFundingHostInfoUseCase
import com.ssafy.fundyou.domain.usecase.funding.GetFundingItemListUseCase

import com.ssafy.fundyou.ui.funding_participate.model.FundingHostUiModel
import com.ssafy.fundyou.ui.funding_participate.model.FundingParticipateItemUiModel
import com.ssafy.fundyou.ui.funding_participate.model.toInvitedFondueHostInfoUiModel
import com.ssafy.fundyou.ui.funding_participate.model.toFundingParticipateItemUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FundingParticipateViewModel @Inject constructor(
    private val getFundingHostInfoUseCase: GetFundingHostInfoUseCase,
    private val getFundingItemListUseCase: GetFundingItemListUseCase
) : ViewModel() {
    private val _fundingHostInfo = SingleLiveEvent<ViewState<FundingHostUiModel>>()
    val fundingHostInfo: LiveData<ViewState<FundingHostUiModel>> get() = _fundingHostInfo

    private val _fundingItemList = SingleLiveEvent<ViewState<List<FundingParticipateItemUiModel>>>()
    val fundingItemList: LiveData<ViewState<List<FundingParticipateItemUiModel>>> get() = _fundingItemList

    fun getFundingHostInfo(fundingId: Long) = viewModelScope.launch {
        _fundingHostInfo.postValue(ViewState.Loading())
        try {
            val response = getFundingHostInfoUseCase(fundingId).toInvitedFondueHostInfoUiModel()
            _fundingHostInfo.postValue(ViewState.Success(response))
        } catch (e: Exception) {
            _fundingHostInfo.postValue(ViewState.Error(e.message))
        }
    }

    fun getFundingItemList(fundingId: Long) = viewModelScope.launch {
        _fundingItemList.postValue(ViewState.Loading())
        try {
            val response = getFundingItemListUseCase(fundingId).map { it.toFundingParticipateItemUiModel() }
            _fundingItemList.postValue(ViewState.Success(response))
        } catch (e: Exception) {
            _fundingItemList.postValue(ViewState.Error(e.message))
        }
    }
}