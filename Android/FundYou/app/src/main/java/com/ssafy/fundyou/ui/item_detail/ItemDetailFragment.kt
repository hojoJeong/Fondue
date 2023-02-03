package com.ssafy.fundyou.ui.item_detail

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentItemDetailBinding
import com.ssafy.fundyou.domain.model.ProductItemModel
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.ui.item_detail.adapter.ItemDetailImgAdapter
import com.ssafy.fundyou.ui.item_detail.adapter.ItemDetailInfoAdapter
import com.ssafy.fundyou.ui.item_detail.adapter.ItemDetailRelatedAdapter
import com.ssafy.fundyou.ui.item_detail.model.ItemDetailInfoModel
import com.ssafy.fundyou.ui.item_detail.model.ItemImgModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailFragment : BaseFragment<FragmentItemDetailBinding>(R.layout.fragment_item_detail) {

    private val relatedAdapter = ItemDetailRelatedAdapter()
    private var itemImgFullState = false
    private var itemInfoImgSize = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        // 상품 전체 정보 넣기
        binding.productInfo = ProductItemModel(0, 100000, "", "BESPOKE 냉장고", false, "삼성", true)
        itemInfoImgSize = binding.ivItemInfo.layoutParams.height

        initItemImgAdapter()
        initItemDetailAdapter()
        initRelatedItemAdapter()
        initMoreItemInfoButtonEvent()
    }

    override fun initViewModels() {

    }

    private fun initMoreItemInfoButtonEvent() {
        binding.tvMoreItemInfoImg.setOnClickListener {
            if (itemImgFullState) setItemInfoImgFixSize()
            else setItemInfoImgWrapContent()

            itemImgFullState = !itemImgFullState
        }
    }

    private fun setItemInfoImgWrapContent() {

        binding.ivItemInfo.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            endToEnd = ConstraintSet.PARENT_ID
            startToStart = ConstraintSet.PARENT_ID
            topToBottom = R.id.div_item_info_img_base
        }

        binding.tvMoreItemInfoImg.text = "상품설명 최소화"
    }

    private fun setItemInfoImgFixSize() {
        val layoutParams = binding.ivItemInfo.layoutParams
        layoutParams.height = itemInfoImgSize
        binding.ivItemInfo.layoutParams = layoutParams

        binding.tvMoreItemInfoImg.text = "상품설명 더보기"
    }

    private fun initRelatedItemAdapter() {
        val tempList = mutableListOf<ProductItemModel>()
        tempList.add(
            ProductItemModel(
                0,
                100000,
                "",
                "BESPOKE 냉장고",
                false,
                "삼성",
                true
            )
        )
        tempList.add(
            ProductItemModel(
                1,
                100000,
                "",
                "BESPOKE 냉장고",
                true,
                "삼성",
                false
            )
        )
        tempList.add(
            ProductItemModel(
                2,
                100000,
                "",
                "BESPOKE 냉장고",
                false,
                "삼성",
                false
            )
        )
        relatedAdapter.submitList(tempList)
        binding.rvRelatedItemList.adapter = relatedAdapter
    }

    private fun initItemDetailAdapter() {
        // 상품 상세정보 임시 리스트
        val tempList = listOf(
            ItemDetailInfoModel(1, "type1", "value1"),
            ItemDetailInfoModel(2, "type2", "value2")
        )
        val itemDetailAdapter = ItemDetailInfoAdapter()

        binding.rvItemInfo.adapter = itemDetailAdapter

        itemDetailAdapter.submitList(tempList)
    }

    private fun initItemImgAdapter() {
        val itemAdapter = ItemDetailImgAdapter()
        // 상품 이미지 임시 리스트
        val itemImgList = listOf(
            ItemImgModel(1, R.drawable.ic_launcher_background, false),
            ItemImgModel(2, R.drawable.bg_banner_ssafylogo2, true)
        )
        itemAdapter.addItemImgList(itemImgList)

        binding.tvItemImgPage.text = "1 / ${itemImgList.size}"

        with(binding.vpItemImg) {
            adapter = itemAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.tvItemImgPage.text =
                        "${(position % itemImgList.size) + 1} / ${itemImgList.size}"
                }
            })
        }
    }
}