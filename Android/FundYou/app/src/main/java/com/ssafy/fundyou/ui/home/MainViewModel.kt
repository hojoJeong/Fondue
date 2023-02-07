package com.ssafy.fundyou.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.usecase.item.GetRandomItemUseCase
import com.ssafy.fundyou.domain.usecase.item.GetRankingItemUseCase
import com.ssafy.fundyou.ui.home.model.RandomItemModel
import com.ssafy.fundyou.ui.home.model.RankingItemModel
import com.ssafy.fundyou.ui.home.model.toRandomItemModel
import com.ssafy.fundyou.ui.home.model.toRankingModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getRankingItemUserCase: GetRankingItemUseCase,
    private val getRandomItemUseCase: GetRandomItemUseCase
) : ViewModel() {

    private val _rankingItemList = MutableLiveData<ViewState<List<RankingItemModel>>>()
    val rankingItemList: LiveData<ViewState<List<RankingItemModel>>>
        get() = _rankingItemList

    private val _randomItemList = MutableLiveData<ViewState<List<RandomItemModel>>>()
    val randomItemList: LiveData<ViewState<List<RandomItemModel>>>
        get() = _randomItemList

    fun getRankingItemList() = viewModelScope.launch {
        _rankingItemList.value = ViewState.Loading()
        try {
            val response = getRankingItemUserCase()
            _rankingItemList.value = ViewState.Success(response.map { it.toRankingModel() })
        } catch (e: Exception){
            _rankingItemList.value = ViewState.Error(e.message)
        }
    }

    fun getRandomItemList() = viewModelScope.launch {
        _randomItemList.value = ViewState.Loading()
        try {
            val response = getRandomItemUseCase()
            _randomItemList.value = ViewState.Success(response.map { it.toRandomItemModel() })
        }catch (e: Exception){
            _randomItemList.value = ViewState.Error(e.message)
        }
    }
}