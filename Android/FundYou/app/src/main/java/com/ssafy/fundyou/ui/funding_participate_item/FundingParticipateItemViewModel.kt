package com.ssafy.fundyou.ui.funding_participate_item

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.SingleLiveEvent
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.usecase.funding.GetFundingItemUseCase
import com.ssafy.fundyou.ui.funding_participate.model.FundingParticipateItemUiModel
import com.ssafy.fundyou.ui.funding_participate.model.toFundingParticipateItemUiModel
import com.ssafy.fundyou.ui.funding_participate_item.model.FundingParticipateItemDetailUiModel
import com.ssafy.fundyou.ui.funding_participate_item.model.toFundingParticipateItemDetailUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FundingParticipateItemViewModel @Inject constructor(
    private val getFundingItemUseCase: GetFundingItemUseCase
) : ViewModel() {
    private val _fundingParticipateItem =
        SingleLiveEvent<ViewState<FundingParticipateItemDetailUiModel>>()
    val fundingParticipateItem: LiveData<ViewState<FundingParticipateItemDetailUiModel>> get() = _fundingParticipateItem

    fun getFundingParticipateItem(fundingItemId: Long) = viewModelScope.launch {
        _fundingParticipateItem.postValue(ViewState.Loading())
        try {
            val response = getFundingItemUseCase(fundingItemId).toFundingParticipateItemDetailUiModel()
            _fundingParticipateItem.postValue(ViewState.Success(response))
        } catch (e: Exception) {
            _fundingParticipateItem.postValue(ViewState.Error(e.message))
        }
    }
}