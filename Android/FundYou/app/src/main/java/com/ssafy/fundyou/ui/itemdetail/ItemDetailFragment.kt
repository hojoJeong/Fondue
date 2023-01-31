package com.ssafy.fundyou.ui.itemdetail

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentItemDetailBinding
import com.ssafy.fundyou.domain.model.ProductItemModel
import com.ssafy.fundyou.ui.search.adapter.SearchResultAdapter
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.ui.itemdetail.adapter.ItemDetailImgAdapter
import com.ssafy.fundyou.ui.itemdetail.adapter.ItemDetailInfoAdapter
import com.ssafy.fundyou.ui.itemdetail.adapter.ItemDetailRelatedAdapter
import com.ssafy.fundyou.ui.itemdetail.model.ItemDetailInfoModel
import com.ssafy.fundyou.ui.itemdetail.model.ItemImgModel
import com.ssafy.fundyou.util.view.RecyclerViewItemDecorator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailFragment : BaseFragment<FragmentItemDetailBinding>(R.layout.fragment_item_detail) {

    private val relatedAdapter = ItemDetailRelatedAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        // 상품 전체 정보 넣기
        binding.productInfo = ProductItemModel(0, 100000, "", "BESPOKE 냉장고", false, "삼성", true)
        initItemImgAdapter()
        initItemDetailAdapter()
        initRelatedItemAdapter()
    }

    override fun initViewModels() {

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
        with(relatedAdapter) {
            submitList(tempList)
        }

        with(binding.rvRelatedItemList){
            adapter = relatedAdapter
            addItemDecoration(RecyclerViewItemDecorator(0,0,30,0))
        }
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