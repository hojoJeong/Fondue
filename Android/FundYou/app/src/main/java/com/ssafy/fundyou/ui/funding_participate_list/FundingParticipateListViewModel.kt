package com.ssafy.fundyou.ui.funding_invited_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.usecase.funding.GetFundingParticipateListUseCase
import com.ssafy.fundyou.ui.funding_invited_list.model.FundingParticipateListUiModel
import com.ssafy.fundyou.ui.funding_invited_list.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FundingParticipateListViewModel @Inject constructor(private val getFundingParticipateListUseCase: GetFundingParticipateListUseCase): ViewModel() {
    private val _invitedFundingOngoingList = MutableLiveData<ViewState<List<FundingParticipateListUiModel>>>()
    val invitedFundingOngoingList: LiveData<ViewState<List<FundingParticipateListUiModel>>>
        get() = _invitedFundingOngoingList

    private val _invitedFundingDoneList = MutableLiveData<ViewState<List<FundingParticipateListUiModel>>>()
    val invitedFundingDoneList: LiveData<ViewState<List<FundingParticipateListUiModel>>>
        get() = _invitedFundingDoneList

    fun getFundingParticipateOngoingList() = viewModelScope.launch {
        _invitedFundingOngoingList.value = ViewState.Loading()
        try{
            val response = getFundingParticipateListUseCase(STATUS_ONGOING)
            _invitedFundingOngoingList.value = ViewState.Success(response.map { it.toUiModel() })
        } catch (e: Exception){
            _invitedFundingOngoingList.value = ViewState.Error(e.message)
        }
    }

    fun getFundingParticipateDoneList() = viewModelScope.launch {
        _invitedFundingDoneList.value = ViewState.Loading()
        try{
            val response = getFundingParticipateListUseCase(STATUS_DONE)
            _invitedFundingDoneList.value = ViewState.Success(response.map { it.toUiModel() })
        } catch (e: Exception){
            _invitedFundingDoneList.value = ViewState.Error(e.message)
        }
    }

    companion object{
        private const val STATUS_ONGOING = 0
        private const val STATUS_DONE = 1
    }
}