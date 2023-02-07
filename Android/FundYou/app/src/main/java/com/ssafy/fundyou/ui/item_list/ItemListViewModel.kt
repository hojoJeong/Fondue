package com.ssafy.fundyou.ui.item_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.usecase.item.GetAllProductItemUseCase
import com.ssafy.fundyou.ui.item_list.Model.ItemListModel
import com.ssafy.fundyou.ui.item_list.Model.toItemListModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ItemListViewModel @Inject constructor(private val getAllProductItemUseCase: GetAllProductItemUseCase) : ViewModel() {
    private val _itemList = MutableLiveData<ViewState<List<ItemListModel>>>()
    val itemList : LiveData<ViewState<List<ItemListModel>>>
        get() = _itemList

    fun getAllItemList() = viewModelScope.launch {
        _itemList.value = ViewState.Loading()
        try {
            val response = getAllProductItemUseCase()
            _itemList.value = ViewState.Success(response.map { it.toItemListModel() })
        } catch (e : Exception){
            _itemList.value = ViewState.Error(e.message)
        }
    }
}