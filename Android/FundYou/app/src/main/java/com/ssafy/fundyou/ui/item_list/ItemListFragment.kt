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
    private val categoryType: ItemListFragmentArgs by navArgs()
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
        initCategory()
        initPriceRange()
    }

    override fun initViewModels() {
        initItemListObserve()
        initCategoryObserve()
        initPriceObserve()
    }

    private fun initCategory() {
        var categoryId =
            resources.getStringArray(R.array.category_num).indexOf(categoryType.categoryType)
        val categoryGroup = binding.chipgItemListCategory
        val hScrollView = binding.hscvItemListCategory
        if (resources.getStringArray(R.array.category_num).contains(categoryType.categoryType)) {
            val category = categoryGroup.getChildAt(categoryId) as Chip
            category.isChecked = true
            hScrollView.post {
                hScrollView.smoothScrollTo(category.x.toInt(), category.y.toInt())
            }
            if (categoryId == 0) {
                itemListViewModel.getAllItemList()
            } else {
                itemListViewModel.setCategory(categoryId)
            }
        }

        /** 카테고리 체크 변화 시 서버 통신 **/
        categoryGroup.setOnCheckedStateChangeListener { group, _ ->
            val checkedChip = group.findViewById(group.checkedChipId) as Chip
            categoryId =
                resources.getStringArray(R.array.category_num).indexOf(checkedChip.text.toString())

            itemListViewModel.setCategory(categoryId)
        }
    }

    private fun initPriceRange() {
        var minPrice = 0
        var maxPrice = 0
        with(binding.sldItemList) {
            addOnChangeListener { slider, _, _ ->
                val sliderValueList = slider.values
                minPrice = sliderValueList[0].toInt()
                maxPrice = sliderValueList[sliderValueList.size - 1].toInt()
                binding.tvItemListPriceRange.text =
                    getString(R.string.title_rank_price_range, minPrice, maxPrice)
            }
            addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: RangeSlider) {}

                override fun onStopTrackingTouch(slider: RangeSlider) {
                    /* 가격 변경 시 서버 통신 */
                    itemListViewModel.setPrice(minPrice * 10000, maxPrice * 10000)
                }
            })
        }
    }

    private fun initCategoryObserve() {
        itemListViewModel.categoryId.observe(viewLifecycleOwner) { categoryId ->
            if(categoryId == 0){
                itemListViewModel.getAllItemList()
            } else {
                itemListViewModel.getCategoryItemList(categoryId)
            }
        }
    }

    private fun initItemListObserve() {
        itemListViewModel.itemList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initItemList: ItemList Loading...")
                }
                is ViewState.Success -> {
                    binding.lyItemListNoContent.root.visibility = View.GONE
                    initItemListAdapter(response.value?: emptyList())
                    if(response.value!!.isEmpty()){
                        binding.lyItemListNoContent.root.visibility = View.VISIBLE
                    }
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initItemList: ItemList Loading Error...${response.message}")
                }
            }
        }
    }

    private fun initPriceObserve(){
        itemListViewModel.sumPrice.observe(viewLifecycleOwner){
            val categoryId = itemListViewModel.categoryId.value ?: 0
            val min = itemListViewModel.minPrice.value ?: 0
            val max = itemListViewModel.maxPrice.value?: 1000000
            itemListViewModel.getItemByPrice(categoryId, min, max)
        }
    }

    private fun initItemListAdapter(itemList: List<ItemListModel>) {
        val itemListAdapter = ItemListAdapter()
        itemListAdapter.submitList(itemList)
        binding.rvItemList.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = itemListAdapter
        }

        itemListAdapter.addLikeItemBtnClickListener { id ->
            itemListViewModel.addLikeItem(id)
        }
    }
}