package com.ssafy.fundyou.ui.itemlist

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.slider.RangeSlider
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentItemListBinding
import com.ssafy.fundyou.domain.model.ProductItemlModel
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.ui.home.adapter.ProductItemAdapter

class ItemListFragment : BaseFragment<FragmentItemListBinding>(R.layout.fragment_item_list) {

    private var productList = mutableListOf<ProductItemlModel>()
    private val categoryType : ItemListFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onViewCreated: ${categoryType.categoryType}")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    override fun initView() {
        initCategory()
        initTitlePriceRange()
        initItemList()
    }

    override fun initViewModels() {

    }

    private fun initCategory() {
        binding.chipgItemListCategory.setOnCheckedStateChangeListener { group, checkedId ->
            //칩 선택시 데이터 갱신을 위한 서버통신
        }
    }

    private fun initTitlePriceRange() {
        val slider = binding.sldItemList

        slider.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: RangeSlider) {
            }

            override fun onStopTrackingTouch(slider: RangeSlider) {
                val priceRange = slider.values
                val minPrice = priceRange[0].toInt()
                val maxPrice = priceRange[priceRange.size - 1].toInt()

                binding.tvItemListPriceRange.text =
                    getString(R.string.title_rank_price_range, minPrice, maxPrice)
            }
        })
    }

    private fun initItemList() {
        //임시 데이터 추가
        with(productList) {
            add(ProductItemlModel(0, "100,000 원", "", "BESPOKE 냉장고", false, "삼성", true))
            add(ProductItemlModel(1, "100,000 원", "", "BESPOKE 냉장고", true, "삼성", false))
            add(ProductItemlModel(2, "100,000 원", "", "BESPOKE 냉장고", false, "삼성", false))
            add(ProductItemlModel(3, "100,000 원", "", "BESPOKE 냉장고", true, "삼성", true))
            add(ProductItemlModel(4, "100,000 원", "", "BESPOKE 냉장고", false, "삼성", true))
            add(ProductItemlModel(5, "100,000 원", "", "BESPOKE 냉장고", true, "삼성", false))
            add(ProductItemlModel(0, "100,000 원", "", "BESPOKE 냉장고", false, "삼성", true))
            add(ProductItemlModel(1, "100,000 원", "", "BESPOKE 냉장고", true, "삼성", false))
            add(ProductItemlModel(2, "100,000 원", "", "BESPOKE 냉장고", false, "삼성", false))
            add(ProductItemlModel(3, "100,000 원", "", "BESPOKE 냉장고", true, "삼성", true))
            add(ProductItemlModel(4, "100,000 원", "", "BESPOKE 냉장고", false, "삼성", true))
            add(ProductItemlModel(5, "100,000 원", "", "BESPOKE 냉장고", true, "삼성", false))
            add(ProductItemlModel(0, "100,000 원", "", "BESPOKE 냉장고", false, "삼성", true))
            add(ProductItemlModel(1, "100,000 원", "", "BESPOKE 냉장고", true, "삼성", false))
            add(ProductItemlModel(2, "100,000 원", "", "BESPOKE 냉장고", false, "삼성", false))
            add(ProductItemlModel(3, "100,000 원", "", "BESPOKE 냉장고", true, "삼성", true))
            add(ProductItemlModel(4, "100,000 원", "", "BESPOKE 냉장고", false, "삼성", true))
            add(ProductItemlModel(5, "100,000 원", "", "BESPOKE 냉장고", true, "삼성", false))
        }

        val itemListAdapter = ProductItemAdapter()
        itemListAdapter.submitList(productList)
        itemListAdapter.setRanking(true)
        binding.rvItemList.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = itemListAdapter
        }
    }
}