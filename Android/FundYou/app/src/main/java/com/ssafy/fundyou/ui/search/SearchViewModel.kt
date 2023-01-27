package com.ssafy.fundyou.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.Constant.SUCCESS
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.usecase.search.DeleteAllHistoryKeywordUseCase
import com.ssafy.fundyou.domain.usecase.search.DeleteSearchHistoryKeywordUseCase
import com.ssafy.fundyou.domain.usecase.search.GetSearchHistoryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchHistoryListUseCase: GetSearchHistoryListUseCase,
    private val deleteSearchHistoryKeywordUseCase: DeleteSearchHistoryKeywordUseCase,
    private val deleteAllHistoryKeywordUseCase: DeleteAllHistoryKeywordUseCase
) : ViewModel() {
    private val _searchKeywordList = MutableLiveData<ViewState<ArrayList<String>>>()
    val searchKeywordList: LiveData<ViewState<ArrayList<String>>> get() = _searchKeywordList

    private val _allRemoveState = MutableLiveData<ViewState<Int>>()
    val allRemoveState: LiveData<ViewState<Int>> get() = _allRemoveState

    private val _keywordRemoveState = MutableLiveData<ViewState<Int>>()
    val keywordRemoveState: LiveData<ViewState<Int>> get() = _keywordRemoveState

    fun getSearchKeywordList() = viewModelScope.launch {
        _searchKeywordList.value = ViewState.Loading()
        try {
            val response = getSearchHistoryListUseCase()
            _searchKeywordList.value = ViewState.Success(response)
        } catch (e: Exception) {
            _searchKeywordList.value = ViewState.Error(e.message)
        }
    }

    fun deleteAllKeyword() =
        viewModelScope.launch {
            _keywordRemoveState.value = ViewState.Loading()
            try {
                val response = deleteAllHistoryKeywordUseCase()
                if (response == SUCCESS) {
                    _keywordRemoveState.value = ViewState.Success(response)
                } else {
                    _keywordRemoveState.value = ViewState.Error("error")
                }
            } catch (e: Exception) {
                _keywordRemoveState.value = ViewState.Error(e.message)
            }
        }

    fun deleteSearchKeyword(baseList: ArrayList<String>, keywordIndex: Int) =
        viewModelScope.launch {
            _allRemoveState.value = ViewState.Loading()
            try {
                val response = deleteSearchHistoryKeywordUseCase(baseList, keywordIndex)
                if (response == SUCCESS) {
                    _allRemoveState.value = ViewState.Success(response)
                } else {
                    _allRemoveState.value = ViewState.Error("error")
                }
            } catch (e: Exception) {
                _allRemoveState.value = ViewState.Error(e.message)
            }
        }
}