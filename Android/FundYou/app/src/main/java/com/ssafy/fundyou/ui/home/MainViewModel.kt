package com.ssafy.fundyou.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.model.item.ProductItemModel
import com.ssafy.fundyou.domain.usecase.item.GetAllProductItemUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getRankingItemUserCase: GetAllProductItemUseCase,
    private val getRandomItemUseCase: GetAllProductItemUseCase
) : ViewModel() {
    private val _rankingItemList = MutableLiveData<ViewState<List<ProductItemModel>>>()
    val rankingItemList: LiveData<ViewState<List<ProductItemModel>>>
        get() = _rankingItemList

    private val _randomItemList = MutableLiveData<ViewState<List<ProductItemModel>>>()
    val randomItemList: LiveData<ViewState<List<ProductItemModel>>>
        get() = _randomItemList

    fun getRankingItemList() = viewModelScope.launch {
        _rankingItemList.value = ViewState.Loading()
        try {
            val response = getRankingItemUserCase()
            _rankingItemList.value = ViewState.Success(response)
        } catch (e: Exception){
            _rankingItemList.value = ViewState.Error(e.message)
        }
    }

    fun getRandomItemList() = viewModelScope.launch {
        _randomItemList.value = ViewState.Loading()
        try {
            val response = getRandomItemUseCase()
            _randomItemList.value = ViewState.Success(response)
        }catch (e: Exception){
            _randomItemList.value = ViewState.Error(e.message)
        }
    }
}