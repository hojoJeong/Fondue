package com.ssafy.fundyou.ui.point

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.usecase.user.LoadPointUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PointViewModel @Inject constructor(private val loadPointUseCase: LoadPointUseCase): ViewModel() {
    private val _loadingPoint = MutableLiveData<Int>()
    val loadingPoint: LiveData<Int>
        get() = _loadingPoint

    fun setPoint(point: Int){
        _loadingPoint.value = point
    }

    private val _resultLoad = MutableLiveData<ViewState<Int>>()
    val resultLoad: LiveData<ViewState<Int>>
        get() = _resultLoad

    fun loadPoint(point: Int) = viewModelScope.launch {
        _resultLoad.value = ViewState.Loading()
        try {
            val response = loadPointUseCase(point)
            Log.d(TAG, "loadPoint: $response")
            _resultLoad.value = ViewState.Success(response)
        } catch (e: Exception){
            _resultLoad.value = ViewState.Error(e.message)
        }
    }
}