package com.ssafy.fundyou.ui.item_list

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.google.android.material.chip.Chip
import com.google.android.material.slider.RangeSlider
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentItemListBinding
import com.ssafy.fundyou.ui.common.BaseFragment
import com.ssafy.fundyou.ui.item_list.adapter.ItemListAdapter
import com.ssafy.fundyou.ui.item_list.model.ItemListModel
import com.ssafy.fundyou.ui.like.LikeItemViewModel

class ItemListFragment : BaseFragment<FragmentItemListBinding>(R.layout.fragment_item_list) {
    private val itemListViewModel by activityViewModels<ItemListViewModel>()
    private val likeItemViewModel by activityViewModels<LikeItemViewModel>()
    private val categoryArg: ItemListFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onViewCreated: ${categoryArg.categoryType}")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initItem()
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
            resources.getStringArray(R.array.category_num).indexOf(categoryArg.categoryType)
        val categoryGroup = binding.chipgItemListCategory
        val hScrollView = binding.hscvItemListCategory
        val category = categoryGroup.getChildAt(categoryId) as Chip
        category.isChecked = true
        hScrollView.post {
            hScrollView.smoothScrollTo(category.x.toInt(), category.y.toInt())
        }
        itemListViewModel.setCategory(categoryId)


        /** 카테고리 체크 변화 시 서버 통신 **/
        categoryGroup.setOnCheckedStateChangeListener { group, _ ->
            val checkedChip = group.findViewById(group.checkedChipId) as Chip
            categoryId =
                resources.getStringArray(R.array.category_num).indexOf(checkedChip.text.toString())

            itemListViewModel.setCategory(categoryId)
        }
    }

    private fun initPriceRange() {
        var minPrice = MIN_PRICE
        var maxPrice = MAX_PRICE
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
                    /** 가격 변경 시 서버 통신 */
                    itemListViewModel.setPrice(minPrice * 10000, maxPrice * 10000)
                }
            })
        }
    }

    private fun initCategoryObserve() {
        itemListViewModel.categoryId.observe(viewLifecycleOwner) { categoryId ->
            initItem()
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
                    initItemListAdapter(response.value ?: emptyList())
                    if (response.value!!.isEmpty()) {
                        binding.lyItemListNoContent.root.visibility = View.VISIBLE
                        binding.lyItemListNoContent.tvNoKeyword.text = "조회된 상품이 없습니다."
                    }
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initItemList: ItemList Loading Error...${response.message}")
                }
            }
        }
    }

    private fun initPriceObserve() {
        itemListViewModel.sumPrice.observe(viewLifecycleOwner) {
            initItem()
        }
    }

    private fun initItemListAdapter(itemList: List<ItemListModel>) {
        val itemListAdapter = ItemListAdapter().apply {
            submitList(itemList)
            addLikeItemBtnClickListener { id ->
                likeItemViewModel.addListItem(id)
            }
            addItemClickListener { id ->
                navigate(ItemListFragmentDirections.actionItemListFragmentToItemDetailFragment(id))
            }

        }
        with(binding.rvItemList) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = itemListAdapter
        }

        val animator = binding.rvItemList.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
    }

    private fun initItem() {
        val categoryId = itemListViewModel.categoryId.value ?: CATEGORY_ALL
        val min = itemListViewModel.minPrice.value ?: MIN_PRICE
        val max = itemListViewModel.maxPrice.value ?: MAX_PRICE
        itemListViewModel.getItemByPrice(categoryId, min, max)
    }

    companion object {
        private const val CATEGORY_ALL = 0
        private const val MIN_PRICE = 0
        private const val MAX_PRICE = 1000000
    }
}