package com.ssafy.fundyou.ui.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.navArgs
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentSearchResultBinding
import com.ssafy.fundyou.domain.model.ProductItemModel
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.ui.search.adapter.SearchResultAdapter
import com.ssafy.fundyou.util.view.RecyclerViewItemDecorator

class SearchResultFragment : BaseFragment<FragmentSearchResultBinding>(R.layout.fragment_search_result) {

    private val resultAdapter = SearchResultAdapter()
    private val args by navArgs<SearchResultFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        binding.editSearch.setText(args.keyword)
        binding.btnSearchBack.setOnClickListener {
            popBackStack()
        }

        initInputTextEvent()
        initSearchResultList()
    }

    override fun initViewModels() {

    }

    private fun initInputTextEvent() {
        binding.editSearch.setOnEditorActionListener { v, actionId, _ ->
            val keyword = v.text.toString()
            if (actionId == EditorInfo.IME_ACTION_SEARCH && keyword != "") {
                // TODO 서버 통신 팔요
                v.text = ""
            }
            true
        }
    }

    private fun initSearchResultList(){
        with(binding.rvSearchItemList) {
            adapter = resultAdapter
            addItemDecoration(RecyclerViewItemDecorator(0,0,30,0,2))
        }
        resultAdapter.submitList(tempData())
    }

    private fun tempData() : List<ProductItemModel>{
        val rankingProductList = mutableListOf<ProductItemModel>()
        rankingProductList.add(ProductItemModel(0, 100000, "", "BESPOKE 냉장고", false, "삼성", true))
        rankingProductList.add(ProductItemModel(1, 100000, "", "BESPOKE 냉장고", true, "삼성", false))
        rankingProductList.add(ProductItemModel(2, 100000, "", "BESPOKE 냉장고", false, "삼성", false))
        rankingProductList.add(ProductItemModel(3, 100000, "", "BESPOKE 냉장고", true, "삼성", true))
        rankingProductList.add(ProductItemModel(4, 100000, "", "BESPOKE 냉장고", false, "삼성", true))
        rankingProductList.add(ProductItemModel(5, 100000, "", "BESPOKE 냉장고", true, "삼성", false))
        return rankingProductList.toList()
    }
}