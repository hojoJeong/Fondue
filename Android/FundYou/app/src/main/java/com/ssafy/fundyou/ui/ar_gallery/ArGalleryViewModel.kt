package com.ssafy.fundyou.ui.ar_gallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
): ViewModel(){
    private val _arImageList = MutableLiveData<ViewState<List<ArImageUIModel>>>()

    var fundingItemId = 0L
    var itemId = 0L

    val arImageList: LiveData<ViewState<List<ArImageUIModel>>> get() = _arImageList

    fun getArImageList(fundingItemId: Long) = viewModelScope.launch {
        _arImageList.value = ViewState.Loading()
        try {
            Log.d("TAG", "getArImageList: ")
            val response =
                getArImageListUseCase(fundingItemId).map { it.toArImageUIModel() }
            _arImageList.value = ViewState.Success(response)
        }catch(e: Exception){
            _arImageList.value = ViewState.Error(e.message)
        }
    }
}