package com.ssafy.fundyou.ui.item_list

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.slider.RangeSlider
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentItemListBinding
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.ui.item_list.adapter.ItemListAdapter
import com.ssafy.fundyou.ui.item_list.model.ItemListModel

class ItemListFragment : BaseFragment<FragmentItemListBinding>(R.layout.fragment_item_list) {
    private val itemListViewModel by activityViewModels<ItemListViewModel>()
    private val categoryType : ItemListFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onViewCreated: ${categoryType.categoryType}")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModels()
    }

    override fun initView() {
        itemListViewModel.getAllItemList()
        initCategory()
        initTitlePriceRange()
    }

    override fun initViewModels() {
        initItemListObserve()
    }

    private fun initCategory() {
        val categoryId = resources.getStringArray(R.array.category_num).indexOf(categoryType.categoryType)
        val categoryGroup = binding.chipgItemListCategory
        val hScrollView = binding.hscvItemListCategory
        if(resources.getStringArray(R.array.category_num).contains(categoryType.categoryType)){
            val category = categoryGroup.getChildAt(categoryId) as Chip
            category.isChecked = true
            hScrollView.post{
                hScrollView.smoothScrollTo(category.x.toInt(), category.y.toInt())
            }
            itemListViewModel.setCategory(categoryId)
        }
//        for(chipNum in 0 until categoryGroup.childCount){
//            val category = categoryGroup.getChildAt(chipNum) as Chip
//            Log.d(TAG, "initCategory: ${category.text}")
//            if(category.text.equals(categoryType.categoryType)){
//                category.isChecked = true
//                hScrollView.post{
//                    hScrollView.smoothScrollTo(category.x.toInt(), category.y.toInt())
//                }
//                break
//            }
//        }
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

    private fun initCategoryObserve(categoryId: Int){
        itemListViewModel.categoryId.observe(viewLifecycleOwner){
            itemListViewModel.getCategoryItemList(categoryId)
        }
    }

    private fun initItemListObserve() {
        itemListViewModel.itemList.observe(viewLifecycleOwner){ response ->
            when(response){
                is ViewState.Loading -> {
                    Log.d(TAG, "initItemList: ItemList Loading...")
                }
                is ViewState.Success -> {
                    initItemListAdapter(response.value ?: emptyList())
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initItemList: ItemList Loading Error...${response.message}")
                }
            }
        }
    }

    private fun initItemListAdapter(itemList: List<ItemListModel>){
        val itemListAdapter = ItemListAdapter()
        itemListAdapter.submitList(itemList)
        binding.rvItemList.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = itemListAdapter
        }
    }
}