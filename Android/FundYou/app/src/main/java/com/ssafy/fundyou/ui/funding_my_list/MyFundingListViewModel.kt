package com.ssafy.fundyou.ui.funding_my_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.usecase.funding.GetMyClosedFundingListUseCase
import com.ssafy.fundyou.domain.usecase.funding.GetMyOngoingFundingUseCase
import com.ssafy.fundyou.ui.funding_my_list.model.MyFundingListUiModel
import com.ssafy.fundyou.ui.funding_my_list.model.toFundingListUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MyFundingListViewModel @Inject constructor(
    private val getMyOngoingFundingUseCase: GetMyOngoingFundingUseCase,
    private val getMyClosedFundingListUseCase: GetMyClosedFundingListUseCase
) : ViewModel() {

    private val _ongoingFunding = MutableLiveData<ViewState<List<MyFundingListUiModel>>>()
    val ongoingFunding: LiveData<ViewState<List<MyFundingListUiModel>>> get() = _ongoingFunding

    private val _closedFunding = MutableLiveData<ViewState<List<MyFundingListUiModel>>>()
    val closedFunding: LiveData<ViewState<List<MyFundingListUiModel>>> get() = _ongoingFunding

    fun getMyOngoingFunding() = viewModelScope.launch {
        _ongoingFunding.value = ViewState.Loading()
        try {
            val response = getMyOngoingFundingUseCase()
            _ongoingFunding.value = ViewState.Success(response.map { it.toFundingListUiModel() })
        } catch (e: Exception) {
            _ongoingFunding.value = ViewState.Error(e.message)
        }
    }

    fun getMyClosedFundingList() = viewModelScope.launch {
        _closedFunding.value = ViewState.Loading()
        try {
            val response = getMyClosedFundingListUseCase()
            _closedFunding.value = ViewState.Success(response.map { it.toFundingListUiModel() })
        } catch (e: Exception) {
            _closedFunding.value = ViewState.Error(e.message)
        }
    }
}