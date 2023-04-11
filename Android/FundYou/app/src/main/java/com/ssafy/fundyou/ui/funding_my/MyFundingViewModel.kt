package com.ssafy.fundyou.ui.funding_my

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.SingleLiveEvent
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.usecase.funding.GetFundingItemListUseCase
import com.ssafy.fundyou.domain.usecase.funding.GetFundingSimpleInfoUseCase
import com.ssafy.fundyou.domain.usecase.funding.TerminateFundingItemUseCase
import com.ssafy.fundyou.ui.funding_my.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyFundingViewModel @Inject constructor(
    private val getFundingSimpleInfoUseCase: GetFundingSimpleInfoUseCase,
    private val getFundingItemListUseCase: GetFundingItemListUseCase,
    private val terminateFundingItemUseCase: TerminateFundingItemUseCase
) : ViewModel() {
    private val _myFundingInfo = SingleLiveEvent<ViewState<MyFundingInfoUiModel>>()
    val myFundingInfo: LiveData<ViewState<MyFundingInfoUiModel>> get() = _myFundingInfo

    private val _myFundingItem = SingleLiveEvent<ViewState<MyFundingItemListUiModel>>()
    val myFundingItem: LiveData<ViewState<MyFundingItemListUiModel>> get() = _myFundingItem

    private val _terminateFundingItemStatus = SingleLiveEvent<ViewState<Boolean>>()
    val terminateFundingItemStatus: LiveData<ViewState<Boolean>> get() = _terminateFundingItemStatus

    fun terminateFundingItem(fundingItemId: Long) = viewModelScope.launch {
        _terminateFundingItemStatus.postValue(ViewState.Loading())
        try {
            val response = terminateFundingItemUseCase(fundingItemId)
            if(response) _terminateFundingItemStatus.postValue(ViewState.Success(response))
            else _terminateFundingItemStatus.postValue(ViewState.Error("NO TERMINATE"))
        } catch (e: Exception) {
            _terminateFundingItemStatus.postValue(ViewState.Error(e.message))
        }
    }

    fun getFundingInfo(fundingId: Long) = viewModelScope.launch {
        _myFundingInfo.postValue(ViewState.Loading())
        try {
            val response = getFundingSimpleInfoUseCase(fundingId)
            _myFundingInfo.postValue(ViewState.Success(response.toMyFundingInfoUiModel()))
        } catch (e: Exception) {
            _myFundingInfo.postValue(ViewState.Error(e.message))
        }
    }

    fun getFundingItemList(fundingId: Long) = viewModelScope.launch {
        _myFundingItem.postValue(ViewState.Loading())
        try {
            val response = getFundingItemListUseCase(fundingId)
            val result = response.map { it.toMyFundingItemUiModel() }.toMyFundingItemListUiModel()
            _myFundingItem.postValue(ViewState.Success(result))
        } catch (e: Exception) {
            _myFundingItem.postValue(ViewState.Error(e.message))
        }
    }
}