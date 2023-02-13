package com.ssafy.fundyou.ui.wishlist

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.SingleLiveEvent
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.usecase.funding.AddFundingUseCase
import com.ssafy.fundyou.domain.usecase.funding.AddItemOngoingFundingUseCase
import com.ssafy.fundyou.domain.usecase.funding.GetMyOngoingFundingUseCase
import com.ssafy.fundyou.domain.usecase.wishlist.AddWishListItemUseCase
import com.ssafy.fundyou.domain.usecase.wishlist.DeleteWishListItemUseCase
import com.ssafy.fundyou.domain.usecase.wishlist.GetWishListItemListUseCase
import com.ssafy.fundyou.domain.usecase.wishlist.ModifyWishListItemUseCase
import com.ssafy.fundyou.ui.wishlist.model.WishListModel
import com.ssafy.fundyou.ui.wishlist.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(
    private val getWishListItemListUseCase: GetWishListItemListUseCase,
    private val deleteWishListItemUseCase: DeleteWishListItemUseCase,
    private val addFundingUseCase: AddFundingUseCase,
    private val getMyOngoingFundingUseCase: GetMyOngoingFundingUseCase,
    private val addItemOngoingFundingUseCase: AddItemOngoingFundingUseCase
) : ViewModel() {
    private val _wishListItem = MutableLiveData<ViewState<List<WishListModel>>>()
    val wishListItem: LiveData<ViewState<List<WishListModel>>>
        get() = _wishListItem

    private val _resultWishList = MutableLiveData<ViewState<Int>>()
    val resultWishList: LiveData<ViewState<Int>>
        get() = _resultWishList

    private val _checkOngoingFunding = SingleLiveEvent<ViewState<Boolean>>()
    val checkOngoingFunding: LiveData<ViewState<Boolean>> get() = _checkOngoingFunding

    private val _addFundingStatus = SingleLiveEvent<ViewState<Long>>()
    val addFundingStatus: LiveData<ViewState<Long>> get() = _addFundingStatus

    private val _addOngoingFundingItemStatus = SingleLiveEvent<ViewState<Long>>()
    val addOngoingFundingItemStatus: LiveData<ViewState<Long>> get() = _addOngoingFundingItemStatus

    fun addOngoingFunding() = viewModelScope.launch {
        _addOngoingFundingItemStatus.postValue(ViewState.Loading())
        try {
            val response = addItemOngoingFundingUseCase()
            _addOngoingFundingItemStatus.postValue(ViewState.Success(response))
        } catch (e: Exception) {
            _addOngoingFundingItemStatus.postValue(ViewState.Error(e.message))
        }
    }

    fun checkOngoingFunding() = viewModelScope.launch {
        _checkOngoingFunding.postValue(ViewState.Loading())
        try {
            val response = getMyOngoingFundingUseCase()
            if (response.isEmpty()) _checkOngoingFunding.postValue(ViewState.Success(false))
            else _checkOngoingFunding.postValue(ViewState.Success(true))
        } catch (e: Exception) {
            _checkOngoingFunding.postValue(ViewState.Error(e.message))
        }
    }

    fun getWishListItemList() = viewModelScope.launch {
        _wishListItem.value = ViewState.Loading()
        try {
            val response = getWishListItemListUseCase()
            _wishListItem.value = ViewState.Success(response.map { it.toUiModel() })
        } catch (e: Exception) {
            _wishListItem.value = ViewState.Error(e.message)
        }
    }

    fun addFunding(endData: Long, fundingName: String) = viewModelScope.launch {
        _addFundingStatus.postValue(ViewState.Loading())
        try {
            val response = addFundingUseCase(endData, fundingName)
            _addFundingStatus.postValue(ViewState.Success(response))
        } catch (e: Exception) {
            _addFundingStatus.postValue(ViewState.Error(e.message))
        }
    }

    fun deleteWishListItem(itemId: Long) = viewModelScope.launch {
        _resultWishList.value = ViewState.Loading()
        try {
            val response = deleteWishListItemUseCase(itemId)
            _resultWishList.value = ViewState.Success(response)
        } catch (e: Exception) {
            _resultWishList.value = ViewState.Error(e.message)
        }
    }
}