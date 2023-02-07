package com.ssafy.fundyou.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.usecase.item.GetFavoriteItemUseCase
import com.ssafy.fundyou.ui.favorite.model.FavoriteModel
import com.ssafy.fundyou.ui.favorite.model.toFavoriteModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val getFavoriteItemUseCase : GetFavoriteItemUseCase) : ViewModel() {
    private val _favoriteItemList = MutableLiveData<ViewState<List<FavoriteModel>>>()
    val favoriteItemList: LiveData<ViewState<List<FavoriteModel>>>
        get() = _favoriteItemList

    fun getFavoriteItemList() = viewModelScope.launch {
        _favoriteItemList.value = ViewState.Loading()
        try {
            val response = getFavoriteItemUseCase()
            _favoriteItemList.value = ViewState.Success(response.map { it.toFavoriteModel() })
        } catch (e: Exception){
            _favoriteItemList.value = ViewState.Error(e.message)
        }
    }
}