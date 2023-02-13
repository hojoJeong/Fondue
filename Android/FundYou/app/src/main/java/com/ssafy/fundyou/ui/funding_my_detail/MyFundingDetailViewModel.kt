package com.ssafy.fundyou.ui.funding_my_detail

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.SingleLiveEvent
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.repository.FundingRepository
import com.ssafy.fundyou.domain.usecase.funding.GetFundingItemListUseCase
import com.ssafy.fundyou.domain.usecase.funding.GetFundingStatisticsUseCase
import com.ssafy.fundyou.ui.funding_my_detail.model.FundingItemStateUiModel
import com.ssafy.fundyou.ui.funding_my_detail.model.FundingParticipateUserUiModel
import com.ssafy.fundyou.ui.funding_my_detail.model.toFundingItemStateUiModel
import com.ssafy.fundyou.ui.funding_my_detail.model.toParticipateUserUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyFundingDetailViewModel @Inject constructor(
    private val getFundingStatisticsUseCase: GetFundingStatisticsUseCase,
    private val getFundingItemListUseCase: GetFundingItemListUseCase
) : ViewModel() {
    private val _fundingStatisticsList =
        SingleLiveEvent<ViewState<List<FundingParticipateUserUiModel>>>()
    val fundingStatisticsList: LiveData<ViewState<List<FundingParticipateUserUiModel>>> get() = _fundingStatisticsList

    private val _fundingItemList = SingleLiveEvent<ViewState<List<FundingItemStateUiModel>>>()
    val fundingItemList: LiveData<ViewState<List<FundingItemStateUiModel>>> get() = _fundingItemList

    fun getFundingStatistics(fundingId: Long) = viewModelScope.launch {
        _fundingStatisticsList.postValue(ViewState.Loading())
        try {
            val response =
                getFundingStatisticsUseCase(fundingId).map { it.toParticipateUserUiModel() }
            _fundingStatisticsList.postValue(ViewState.Success(response))
        } catch (e: Exception) {
            _fundingStatisticsList.postValue(ViewState.Error(e.message))
        }
    }

    fun getFundingItemList(fundingId: Long) = viewModelScope.launch {
        _fundingItemList.postValue(ViewState.Loading())
        try {
            val response =
                getFundingItemListUseCase(fundingId).map { it.toFundingItemStateUiModel() }
            _fundingItemList.postValue(ViewState.Success(response))
        } catch (e: Exception) {
            _fundingItemList.postValue(ViewState.Error(e.message))
        }
    }
}