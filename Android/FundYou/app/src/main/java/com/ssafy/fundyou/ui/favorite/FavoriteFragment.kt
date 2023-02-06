package com.ssafy.fundyou.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentFavoriteBinding
import com.ssafy.fundyou.domain.model.ProductItemModel
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.ui.home.adapter.ProductItemAdapter

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {
    private val itemList = mutableListOf<ProductItemModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    override fun initView() {
        initItemList()
    }

    override fun initViewModels() {

    }

    private fun initItemList(){
        with(itemList) {
            add(ProductItemModel(0, 100000, "", "BESPOKE 냉장고", false, "삼성", true))
            add(ProductItemModel(1, 100000, "", "BESPOKE 냉장고", true, "삼성", false))
            add(ProductItemModel(2, 100000, "", "BESPOKE 냉장고", false, "삼성", false))
            add(ProductItemModel(3, 100000, "", "BESPOKE 냉장고", true, "삼성", true))
            add(ProductItemModel(4, 100000, "", "BESPOKE 냉장고", false, "삼성", true))
            add(ProductItemModel(5, 100000, "", "BESPOKE 냉장고", true, "삼성", false))
            add(ProductItemModel(0, 100000, "", "BESPOKE 냉장고", false, "삼성", true))
            add(ProductItemModel(1, 100000, "", "BESPOKE 냉장고", true, "삼성", false))
            add(ProductItemModel(3, 100000, "", "BESPOKE 냉장고", true, "삼성", true))
            add(ProductItemModel(2, 100000, "", "BESPOKE 냉장고", false, "삼성", false))
            add(ProductItemModel(4, 100000, "", "BESPOKE 냉장고", false, "삼성", true))
            add(ProductItemModel(5, 100000, "", "BESPOKE 냉장고", true, "삼성", false))
            add(ProductItemModel(0, 100000, "", "BESPOKE 냉장고", false, "삼성", true))
            add(ProductItemModel(1, 100000, "", "BESPOKE 냉장고", true, "삼성", false))
            add(ProductItemModel(2, 100000, "", "BESPOKE 냉장고", false, "삼성", false))
            add(ProductItemModel(3, 100000, "", "BESPOKE 냉장고", true, "삼성", true))
            add(ProductItemModel(4, 100000, "", "BESPOKE 냉장고", false, "삼성", true))
            add(ProductItemModel(5, 100000, "", "BESPOKE 냉장고", true, "삼성", false))
        }

        val itemListAdapter = ProductItemAdapter()
        itemListAdapter.submitList(itemList)
        itemListAdapter.setFavoriteVisibility(false)

        with(binding.rvFavorite){
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = itemListAdapter
        }
    }

}