package com.ssafy.fundyou.ui.wishlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.usecase.wishlist.AddWishListItemUseCase
import com.ssafy.fundyou.domain.usecase.wishlist.DeleteWishListItemUseCase
import com.ssafy.fundyou.domain.usecase.wishlist.GetWishListItemListUseCase
import com.ssafy.fundyou.domain.usecase.wishlist.ModifyWishListItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(
    private val getWishListItemListUseCase: GetWishListItemListUseCase,
    private val addWishListItemUseCase: AddWishListItemUseCase,
    private val modifyWishListItemUseCase: ModifyWishListItemUseCase,
    private val deleteWishListItemUseCase: DeleteWishListItemUseCase
) : ViewModel() {
    private val _wishListItem = MutableLiveData<ViewState<List<WishListModel>>>()
    val wishListItem: LiveData<ViewState<List<WishListModel>>>
        get() = _wishListItem

    private val _resultWishList = MutableLiveData<ViewState<Int>>()
    val resultWishList: LiveData<ViewState<Int>>
        get() = _resultWishList

    fun getWishListItemList() = viewModelScope.launch {
        _wishListItem.value = ViewState.Loading()
        try {
            val response = getWishListItemListUseCase()
            _wishListItem.value = ViewState.Success(response.map { it.toUiModel() })
        } catch (e: Exception) {
            _wishListItem.value = ViewState.Error(e.message)
        }
    }

    fun addWishListItem(count: Int, itemId: Int) = viewModelScope.launch {
        _resultWishList.value = ViewState.Loading()
        try {
            val response = addWishListItemUseCase(count, itemId)
            _resultWishList.value = ViewState.Success(response)
        } catch (e: Exception) {
            _resultWishList.value = ViewState.Error(e.message)
        }
    }

    fun modifyWishListItem(count: Int, itemId: Int) = viewModelScope.launch {
        _resultWishList.value = ViewState.Loading()
        try {
            val response = modifyWishListItemUseCase(count, itemId)
            _resultWishList.value = ViewState.Success(response)
        } catch (e: Exception) {
            _resultWishList.value = ViewState.Error(e.message)
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