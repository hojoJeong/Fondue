package com.ssafy.fundyou.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentSearchBinding
import com.ssafy.fundyou.ui.common.adapter.PopularSearchKeywordAdapter
import com.ssafy.fundyou.ui.common.BaseFragment
import com.ssafy.fundyou.ui.search.adapter.SearchHistoryKeywordAdapter
import com.ssafy.fundyou.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val viewModels by viewModels<SearchViewModel>()
    private var isExistKeyword = false
    private val recentKeywordAdapter = SearchHistoryKeywordAdapter()
    private val popularKeywordAdapter = PopularSearchKeywordAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        initInputTextEvent()
        initRecentKeywordList()
        initDeleteAllRecentKeyword()
        initPopularKeywordList()
    }

    override fun initViewModels() {
        initGetSearchKeywordObserve()
        initDeleteSearchKeywordStateObserve()
        initAddSearchKeywordStateObserve()
        initGetPopularKeywordListObserve()
    }

    private fun initGetPopularKeywordListObserve() {
        viewModels.popularKeywordList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initGetPopularKeywordListObserve: Loading...")
                }
                is ViewState.Success -> {
                    popularKeywordAdapter.submitList(response.value)
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initGetPopularKeywordListObserve: Error... ${response.message}")
                }
            }
        }
    }

    private fun initRecentKeywordList() {
        viewModels.getSearchKeywordList()
        with(recentKeywordAdapter) {
            addItemClickEvent { currentList, index ->
                viewModels.deleteSearchKeyword(baseList = currentList, keywordIndex = index)
            }

            addItemDeleteEvent { keyword ->
                navigate(SearchFragmentDirections.actionSearchFragmentToSearchResultFragment(keyword))
            }
        }
        binding.rvRecentSearchKeyword.adapter = recentKeywordAdapter
    }

    private fun initDeleteAllRecentKeyword() {
        binding.tvAllDeleteRecentKeyword.setOnClickListener {
            if (isExistKeyword) viewModels.deleteAllKeyword()
        }
    }

    private fun initInputTextEvent() {
        binding.editSearch.setOnEditorActionListener { v, actionId, _ ->
            val keyword = v.text.toString()
            if (actionId == EditorInfo.IME_ACTION_SEARCH && keyword != "") {
                viewModels.addSearchKeywordList(keyword)
                v.text = ""
                navigate(SearchFragmentDirections.actionSearchFragmentToSearchResultFragment(keyword))
            }
            true
        }
    }

    private fun initPopularKeywordList() {
        viewModels.getPopularKeywordList()
        popularKeywordAdapter.addItemClickEvent { keyword ->
            navigate(SearchFragmentDirections.actionSearchFragmentToSearchResultFragment(keyword))
        }
        binding.rvPopularKeyword.adapter = popularKeywordAdapter
    }

    private fun initGetSearchKeywordObserve() {
        viewModels.searchKeywordList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initGetSearchKeywordObserve: loading")
                }
                is ViewState.Success -> {
                    val result = response.value ?: emptyList()
                    if (result.isEmpty()) {
                        binding.lySearchNoKeyword.root.visibility = View.VISIBLE
                        isExistKeyword = false
                    } else {
                        isExistKeyword = true
                    }
                    with(recentKeywordAdapter) {
                        addKeywordList(result)
                        refreshAdapter()
                    }
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initGetSearchKeywordObserve: error ")
                }
            }
        }
    }

    private fun initAddSearchKeywordStateObserve() {
        viewModels.keywordAddState.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initAddSearchKeywordStateObserve: loading")
                }
                is ViewState.Success -> {
                    binding.lySearchNoKeyword.root.visibility = View.INVISIBLE
                    viewModels.getSearchKeywordList()
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initAddSearchKeywordStateObserve: error")
                }
            }
        }
    }

    private fun initDeleteSearchKeywordStateObserve() {
        viewModels.allRemoveState.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initDeleteSearchKeywordStateObserve: loading")
                }
                is ViewState.Success -> {
                    requireContext().showToast("삭제 완료되었습니다.")
                    viewModels.getSearchKeywordList()
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initDeleteSearchKeywordStateObserve: error")
                }
            }
        }

        viewModels.keywordRemoveState.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initDeleteSearchKeywordStateObserve: loading")
                }
                is ViewState.Success -> {
                    requireContext().showToast("검색 초기화 완료되었습니다.")
                    viewModels.getSearchKeywordList()
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initDeleteSearchKeywordStateObserve: error")
                }
            }
        }
    }

    companion object {
        private const val TAG = "SearchFragment..."
    }
}