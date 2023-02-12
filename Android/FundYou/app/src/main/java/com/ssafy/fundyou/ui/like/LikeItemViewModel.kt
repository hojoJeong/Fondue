package com.ssafy.fundyou.ui.like

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.SingleLiveEvent
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
    private val _likeItemList = SingleLiveEvent<ViewState<List<LikeItemModel>>>()
    val likeItemList: LiveData<ViewState<List<LikeItemModel>>>
        get() = _likeItemList

    private val _resultModifyLikeItem = SingleLiveEvent<ViewState<Int>>()
    val resultModifyListItem: LiveData<ViewState<Int>>
        get() = _resultModifyLikeItem

    fun getLikeItemList() = viewModelScope.launch {
        _likeItemList.postValue(ViewState.Loading())
        try {
            val response = getLikeItemUseCase()
            _likeItemList.postValue(ViewState.Success(response.map { it.toLikeItemModel() }))
        } catch (e: Exception) {
            _likeItemList.postValue(ViewState.Error(e.message))
        }
    }

    fun addListItem(itemId: Long) = viewModelScope.launch {
        _resultModifyLikeItem.postValue(ViewState.Loading())
        try {
            val response = addListItemUseCase(itemId)
            _resultModifyLikeItem.postValue(ViewState.Success(response))
        } catch (e: Exception){
            _resultModifyLikeItem.postValue(ViewState.Error(e.message))
        }
    }

}