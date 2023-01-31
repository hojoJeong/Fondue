package com.ssafy.fundyou.ui.itemdetail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentItemDetailBinding
import com.ssafy.fundyou.domain.model.ProductItemModel
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.ui.itemdetail.adapter.ItemDetailImgAdapter
import com.ssafy.fundyou.ui.itemdetail.model.ItemImgModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailFragment : BaseFragment<FragmentItemDetailBinding>(R.layout.fragment_item_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        binding.productInfo = ProductItemModel(0, 100000, "", "BESPOKE 냉장고", false, "삼성", true)
        initItemImgAdapter()
    }

    override fun initViewModels() {

    }

    private fun initItemImgAdapter() {
        val itemAdapter = ItemDetailImgAdapter()
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