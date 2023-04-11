package com.ssafy.fundyou.ui.search

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.fundyou.common.Constant.SUCCESS
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.domain.model.search.PopularKeywordEntity
import com.ssafy.fundyou.domain.usecase.item.GetKeywordItemListUseCase
import com.ssafy.fundyou.domain.usecase.search.*
import com.ssafy.fundyou.ui.search.model.SearchItemModel
import com.ssafy.fundyou.ui.search.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val addSearchHistoryKeyword: AddSearchHistoryKeyword,
    private val getSearchHistoryListUseCase: GetSearchHistoryListUseCase,
    private val deleteSearchHistoryKeywordUseCase: DeleteSearchHistoryKeywordUseCase,
    private val deleteAllHistoryKeywordUseCase: DeleteAllHistoryKeywordUseCase,
    private val getPopularKeywordListUseCase: GetPopularKeywordListUseCase,
    private val getKeywordItemListUseCase: GetKeywordItemListUseCase
) : ViewModel() {
    private val _searchKeywordList = MutableLiveData<ViewState<List<String>>>()
    val searchKeywordList: LiveData<ViewState<List<String>>> get() = _searchKeywordList

    private val _allRemoveState = MutableLiveData<ViewState<Int>>()
    val allRemoveState: LiveData<ViewState<Int>> get() = _allRemoveState

    private val _keywordRemoveState = MutableLiveData<ViewState<Int>>()
    val keywordRemoveState: LiveData<ViewState<Int>> get() = _keywordRemoveState

    private val _keywordAddState = MutableLiveData<ViewState<Int>>()
    val keywordAddState: LiveData<ViewState<Int>> get() = _keywordAddState

    private val _popularKeywordList = MutableLiveData<ViewState<List<PopularKeywordEntity>>>()
    val popularKeywordList: LiveData<ViewState<List<PopularKeywordEntity>>> get() = _popularKeywordList

    private val _keywordItemList = MutableLiveData<ViewState<List<SearchItemModel>>>()
    val keywordItemList: LiveData<ViewState<List<SearchItemModel>>> get() = _keywordItemList

    fun getSearchItemList(keyword: String, minPrice: Int, maxPrice: Int) = viewModelScope.launch {
        _keywordItemList.value = ViewState.Loading()
        try {
            val response =
                getKeywordItemListUseCase(keyword, minPrice, maxPrice).map { it.toUiModel() }
            _keywordItemList.value = ViewState.Success(response)
        } catch (e: Exception) {
            _keywordItemList.value = ViewState.Error(e.message)
        }
    }

    fun getPopularKeywordList() = viewModelScope.launch {
        _popularKeywordList.value = ViewState.Loading()
        try {
            val response = getPopularKeywordListUseCase()
            _popularKeywordList.value = ViewState.Success(response)
        } catch (e: Exception) {
            _popularKeywordList.value = ViewState.Error(e.message)
        }
    }

    fun getSearchKeywordList() = viewModelScope.launch {
        _searchKeywordList.value = ViewState.Loading()
        try {
            val response = getSearchHistoryListUseCase()
            _searchKeywordList.value = ViewState.Success(response)
        } catch (e: Exception) {
            _searchKeywordList.value = ViewState.Error(e.message)
        }
    }

    fun addSearchKeywordList(keyword: String) = viewModelScope.launch {
        _keywordAddState.value = ViewState.Loading()
        try {
            val response = addSearchHistoryKeyword(keyword)
            if (response == SUCCESS) {
                _keywordAddState.value = ViewState.Success(response)
            } else {
                _keywordAddState.value = ViewState.Error("error")
            }
        } catch (e: Exception) {
            _keywordAddState.value = ViewState.Error(e.message)
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

    fun deleteSearchKeyword(baseList: List<String>, keywordIndex: Int) =
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