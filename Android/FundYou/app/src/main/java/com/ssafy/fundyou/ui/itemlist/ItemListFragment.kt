package com.ssafy.fundyou.ui.itemlist

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.slider.RangeSlider
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentItemListBinding
import com.ssafy.fundyou.domain.model.ProductItemModel
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.ui.home.adapter.ProductItemAdapter

class ItemListFragment : BaseFragment<FragmentItemListBinding>(R.layout.fragment_item_list) {

    private var productList = mutableListOf<ProductItemModel>()
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
        val categoryGroup = binding.chipgItemListCategory
        val hScrollView = binding.hscvItemListCategory
        for(chipNum in 0 until categoryGroup.childCount){
            val category = categoryGroup.getChildAt(chipNum) as Chip
            Log.d(TAG, "initCategory: ${category.text}")
            if(category.text.equals(categoryType.categoryType)){
                category.isChecked = true
                hScrollView.post{
                    hScrollView.smoothScrollTo(category.x.toInt(), category.y.toInt())
                }

                //TODO("상품 목록 초기 진입 시 카테고리에 따른 데이터 호출")
                break
            }
        }
        categoryGroup.setOnCheckedStateChangeListener { group, checkedId ->
            //TODO("칩 변경 시 서버통신")
        }
    }

    private fun initTitlePriceRange() {
        with(binding.sldItemList) {
            addOnChangeListener { slider, _, _ ->
                val sliderValueList = slider.values
                val minPrice = sliderValueList[0].toInt()
                val maxPrice = sliderValueList[sliderValueList.size - 1].toInt()
                binding.tvItemListPriceRange.text =
                    getString(R.string.title_rank_price_range, minPrice, maxPrice)
            }
            addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: RangeSlider) {

                }

                override fun onStopTrackingTouch(slider: RangeSlider) {

                    //TODO("가격 변경 시 서버통신")
                }
            })
        }
    }

    private fun initItemList() {
        //임시 데이터 추가
        with(productList) {
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
        itemListAdapter.submitList(productList)
        binding.rvItemList.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = itemListAdapter
        }
    }
}