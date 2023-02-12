package com.ssafy.fundyou.ui.funding_my

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.usecase.funding.GetFundingItemListUseCase
import com.ssafy.fundyou.domain.usecase.funding.GetFundingSimpleInfoUseCase
import com.ssafy.fundyou.ui.funding_my.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyFundingViewModel @Inject constructor(
    private val getFundingSimpleInfoUseCase: GetFundingSimpleInfoUseCase,
    private val getFundingItemListUseCase: GetFundingItemListUseCase
) : ViewModel() {
    private val _myFundingInfo = MutableLiveData<ViewState<MyFundingInfoUiModel>>()
    val myFundingInfo: LiveData<ViewState<MyFundingInfoUiModel>> get() = _myFundingInfo

    private val _myFundingItem = MutableLiveData<ViewState<MyFundingItemListUiModel>>()
    val myFundingItem: LiveData<ViewState<MyFundingItemListUiModel>> get() = _myFundingItem

    fun getFundingInfo(fundingId: Long) = viewModelScope.launch {
        _myFundingInfo.value = ViewState.Loading()
        try {
            val response = getFundingSimpleInfoUseCase(fundingId)
            _myFundingInfo.value = ViewState.Success(response.toMyFundingInfoUiModel())
        } catch (e: Exception) {
            _myFundingInfo.value = ViewState.Error(e.message)
        }
    }

    fun getFundingItemList(fundingId: Long) = viewModelScope.launch {
        _myFundingItem.value = ViewState.Loading()
        try {
            val response = getFundingItemListUseCase(fundingId)
            val result = response.map { it.toMyFundingItemUiModel() }.toMyFundingItemListUiModel()
            _myFundingItem.value = ViewState.Success(result)
        } catch (e: Exception) {
            _myFundingItem.value = ViewState.Error(e.message)
        }
    }
}