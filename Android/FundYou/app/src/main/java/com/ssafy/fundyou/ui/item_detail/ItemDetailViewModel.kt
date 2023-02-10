package com.ssafy.fundyou.ui.item_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.usecase.item.GetItemDetailInfoUseCase
import com.ssafy.fundyou.domain.usecase.item.GetRandomItemUseCase
import com.ssafy.fundyou.ui.item_detail.model.ItemDetailModel
import com.ssafy.fundyou.ui.item_detail.model.RelatedItemModel
import com.ssafy.fundyou.ui.item_detail.model.toItemDetailModel
import com.ssafy.fundyou.ui.item_detail.model.toRelatedItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemDetailViewModel @Inject constructor(
    private val getItemDetailInfoUseCase: GetItemDetailInfoUseCase,
    private val getRandomItemUseCase: GetRandomItemUseCase
) : ViewModel() {
    private val _itemDetailInfo = MutableLiveData<ViewState<ItemDetailModel>>()
    val itemDetailInfo: LiveData<ViewState<ItemDetailModel>> get() = _itemDetailInfo

    private val _relatedItemList = MutableLiveData<ViewState<List<RelatedItemModel>>>()
    val relatedItemList: LiveData<ViewState<List<RelatedItemModel>>> get() = _relatedItemList

    fun getItemDetailInfo(itemId: Long) = viewModelScope.launch {
        _itemDetailInfo.value = ViewState.Loading()
        try {
            val response = getItemDetailInfoUseCase(itemId).toItemDetailModel()
            _itemDetailInfo.value = ViewState.Success(response)
        } catch (e: Exception) {
            _itemDetailInfo.value = ViewState.Error(e.message)
        }
    }

    fun getRelatedItemList() = viewModelScope.launch {
        _relatedItemList.value = ViewState.Loading()
        try {
            val result = getRandomItemUseCase().map { it.toRelatedItemModel() }
            _relatedItemList.value = ViewState.Success(result)
        } catch (e: Exception) {
            _relatedItemList.value = ViewState.Error(e.message)
        }
    }
}