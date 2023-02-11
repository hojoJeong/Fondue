package com.ssafy.fundyou.ui.like

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.usecase.item.AddLikeItemUseCase
import com.ssafy.fundyou.domain.usecase.item.GetLikeItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LikeItemViewModel @Inject constructor(
    private val getLikeItemUseCase: GetLikeItemUseCase,
    private val addListItemUseCase: AddLikeItemUseCase
) :
    ViewModel() {
    private val _likeItemList = MutableLiveData<ViewState<List<LikeItemModel>>>()
    val likeItemList: LiveData<ViewState<List<LikeItemModel>>>
        get() = _likeItemList

    private val _resultAddLikeItem = MutableLiveData<ViewState<Int>>()
    val resultAddListItem: LiveData<ViewState<Int>>
        get() = _resultAddLikeItem

    fun getLikeItemList() = viewModelScope.launch {
        _likeItemList.value = ViewState.Loading()
        try {
            val response = getLikeItemUseCase()
            _likeItemList.value = ViewState.Success(response.map { it.toLikeItemModel() })
        } catch (e: Exception) {
            _likeItemList.value = ViewState.Error(e.message)
        }
    }

    fun addListItem(itemId: Long) = viewModelScope.launch {
        _resultAddLikeItem.value = ViewState.Loading()
        try {
            val response = addListItemUseCase(itemId)
            _resultAddLikeItem.value = ViewState.Success(response)
        } catch (e: Exception){
            _resultAddLikeItem.value = ViewState.Error(e.message)
        }
    }

}