package com.ssafy.fundyou.ui.item_list

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.usecase.item.GetAllProductItemUseCase
import com.ssafy.fundyou.domain.usecase.item.GetCategoryItemUseCase
import com.ssafy.fundyou.ui.item_list.model.ItemListModel
import com.ssafy.fundyou.ui.item_list.model.toItemListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(
    private val getAllProductItemUseCase: GetAllProductItemUseCase,
    private val getCategoryItemUseCase: GetCategoryItemUseCase
) : ViewModel() {
    private val _itemList = MutableLiveData<ViewState<List<ItemListModel>>>()
    val itemList: LiveData<ViewState<List<ItemListModel>>>
        get() = _itemList

    private val _categoryId = MutableLiveData<Int>()
    val categoryId: LiveData<Int>
        get() = _categoryId

    fun getAllItemList() = viewModelScope.launch {
        _itemList.value = ViewState.Loading()
        try {
            val response = getAllProductItemUseCase()
            _itemList.value = ViewState.Success(response.map { it.toItemListModel() })
            Log.d(TAG, "getAllItemList: ${itemList.value }")
        } catch (e: Exception) {
            _itemList.value = ViewState.Error(e.message)
        }
    }

    fun setCategory(categoryId: Int) {
        _categoryId.value = categoryId
    }

    fun getCategoryItemList(categoryId: Int) = viewModelScope.launch {
        _itemList.value = ViewState.Loading()
        try {
            val response = getCategoryItemUseCase(1)
            _itemList.value = ViewState.Success(response.map { it.toItemListModel() })
        } catch (e: Exception) {
            _itemList.value = ViewState.Error(e.message)
        }
    }
}