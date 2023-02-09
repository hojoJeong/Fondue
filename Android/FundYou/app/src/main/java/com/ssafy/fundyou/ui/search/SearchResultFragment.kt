package com.ssafy.fundyou.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.slider.RangeSlider
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentSearchResultBinding
import com.ssafy.fundyou.domain.model.item.ProductItemModel
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.ui.search.adapter.SearchResultAdapter
import com.ssafy.fundyou.util.view.RecyclerViewItemDecorator
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.max

@AndroidEntryPoint
class SearchResultFragment :
    BaseFragment<FragmentSearchResultBinding>(R.layout.fragment_search_result) {

    private val resultAdapter = SearchResultAdapter()
    private val args by navArgs<SearchResultFragmentArgs>()
    private val searchViewModel by viewModels<SearchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        searchViewModel.getSearchItemList(args.keyword, 0, Int.MAX_VALUE)
        binding.editSearch.setText(args.keyword)
        binding.btnSearchBack.setOnClickListener {
            popBackStack()
        }

        initInputTextEvent()
        initSearchResultList()
        initTitlePriceRange()
    }

    override fun initViewModels() {
        initSearchViewModel()
    }

    private fun initSearchViewModel() {
        searchViewModel.keywordItemList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initSearchViewModel: loading...")
                }
                is ViewState.Success -> {
                    resultAdapter.submitList(emptyList())
                    if (response.value?.size!! > 0) {
                        binding.tvMainRankPriceRange.visibility = View.VISIBLE
                        binding.sldMainRank.visibility = View.VISIBLE
                        binding.lyNoContent.root.visibility = View.GONE
                        resultAdapter.submitList(response.value ?: emptyList())
                    } else {
                        binding.tvMainRankPriceRange.visibility = View.GONE
                        binding.sldMainRank.visibility = View.GONE
                        binding.lyNoContent.root.visibility = View.VISIBLE
                    }
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initSearchViewModel: error...")
                }
            }
        }
    }

    private fun initTitlePriceRange() {
        with(binding.sldMainRank) {
            addOnChangeListener { slider, _, _ ->
                val sliderValueList = slider.values
                val minPrice = sliderValueList[0].toInt()
                val maxPrice = sliderValueList[sliderValueList.size - 1].toInt()
                binding.tvMainRankPriceRange.text =
                    getString(R.string.title_rank_price_range, minPrice, maxPrice)
            }
            addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: RangeSlider) {

                }

                override fun onStopTrackingTouch(slider: RangeSlider) {
                    val sliderValueList = slider.values
                    searchViewModel.getSearchItemList(
                        keyword = binding.editSearch.text.toString(),
                        minPrice = sliderValueList[0].toInt() * 100000,
                        maxPrice = sliderValueList[sliderValueList.size - 1].toInt() * 1000000
                    )
                }
            })
        }
    }

    private fun initInputTextEvent() {
        binding.editSearch.setOnEditorActionListener { v, actionId, _ ->
            val keyword = v.text.toString()
            if (actionId == EditorInfo.IME_ACTION_SEARCH && keyword != "") {
                v.text = ""
                searchViewModel.getSearchItemList(keyword, 0, Int.MAX_VALUE)
            }
            true
        }
    }

    private fun initSearchResultList() {
        resultAdapter.initItemClickEvent { itemId ->
            navigate(
                SearchResultFragmentDirections.actionSearchResultFragmentToItemDetailFragment(
                    itemId
                )
            )
        }

        with(binding.rvSearchItemList) {
            adapter = resultAdapter
            addItemDecoration(RecyclerViewItemDecorator(0, 0, 30, 0, 2))
        }
    }

    companion object {
        private const val TAG = "SearchResultFragment..."
    }
}