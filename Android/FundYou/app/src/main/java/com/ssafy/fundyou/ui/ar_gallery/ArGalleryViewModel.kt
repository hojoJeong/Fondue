package com.ssafy.fundyou.ui.ar_gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.SingleLiveEvent
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.usecase.ar.GetArImageListUseCase
import com.ssafy.fundyou.domain.usecase.ar.SaveArImageUseCase
import com.ssafy.fundyou.ui.ar_gallery.model.ArImageUIModel
import com.ssafy.fundyou.ui.ar_gallery.model.toArImageUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArGalleryViewModel @Inject constructor(
    private val getArImageListUseCase: GetArImageListUseCase,
    private val saveArImageUseCase: SaveArImageUseCase
): ViewModel(){
    private val _arImageList = SingleLiveEvent<ViewState<List<ArImageUIModel>>>()
    val arImageList: LiveData<ViewState<List<ArImageUIModel>>> get() = _arImageList

    fun getArImageList(fundingItemId: Long) = viewModelScope.launch {
        _arImageList.postValue(ViewState.Loading())
        try {
            val response =
                getArImageListUseCase(fundingItemId).map { it.toArImageUIModel() }
            _arImageList.postValue(ViewState.Success(response))
        }catch(e: Exception){
            _arImageList.postValue(ViewState.Error(e.message))
        }
    }
}