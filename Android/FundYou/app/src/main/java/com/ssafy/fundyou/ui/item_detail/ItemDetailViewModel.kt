package com.ssafy.fundyou.ui.item_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.usecase.item.GetItemDetailInfoUseCase
import com.ssafy.fundyou.ui.item_detail.model.ItemDetailModel
import com.ssafy.fundyou.ui.item_detail.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemDetailViewModel @Inject constructor(
    private val getItemDetailInfoUseCase: GetItemDetailInfoUseCase
) : ViewModel() {
    private val _itemDetailInfo = MutableLiveData<ViewState<ItemDetailModel>>()
    val itemDetailInfo: LiveData<ViewState<ItemDetailModel>> get() = _itemDetailInfo

    fun getItemDetailInfo(itemId: Long) = viewModelScope.launch {
        _itemDetailInfo.value = ViewState.Loading()
        try {
            val response = getItemDetailInfoUseCase(itemId).toUiModel()
            _itemDetailInfo.value = ViewState.Success(response)
        } catch (e: Exception) {
            _itemDetailInfo.value = ViewState.Error(e.message)
        }
    }
}