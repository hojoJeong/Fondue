package com.ssafy.fundyou.ui.ar_capture_confirm

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.domain.usecase.ar.SaveArImageUseCase
import com.ssafy.fundyou.ui.ar_gallery.model.toArImageUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArCaptureViewModel @Inject constructor(private val saveArImageUseCase: SaveArImageUseCase) :
    ViewModel() {
    private val _arImageSaveRequestStatus = MutableLiveData<Boolean>()
    val arImageSaveRequestStatus: LiveData<Boolean> get() = _arImageSaveRequestStatus

    fun saveArImage(fundingItemId: Long, url: String) = viewModelScope.launch {
        _arImageSaveRequestStatus.postValue(false)
        try {
            val response = saveArImageUseCase(fundingItemId, url).toArImageUIModel()
            _arImageSaveRequestStatus.postValue(true)
        } catch (e: Exception) {
            _arImageSaveRequestStatus.postValue(false)
        }
    }
}