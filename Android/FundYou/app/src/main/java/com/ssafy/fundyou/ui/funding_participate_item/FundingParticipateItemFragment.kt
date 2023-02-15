package com.ssafy.fundyou.ui.funding_participate_item

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.showAlignTop
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentFundingParticipateItemBinding
import com.ssafy.fundyou.ui.common.BaseFragment
import com.ssafy.fundyou.ui.funding_participate_item.model.FundingParticipateItemDetailUiModel
import com.ssafy.fundyou.ui.item_detail.adapter.ItemDetailDescriptionInfoAdapter
import com.ssafy.fundyou.ui.item_detail.adapter.ItemDetailImgAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FundingParticipateItemFragment :
    BaseFragment<FragmentFundingParticipateItemBinding>(R.layout.fragment_funding_participate_item) {

    private val argument by navArgs<FundingParticipateItemFragmentArgs>()
    private val fundingParticipateItemViewModel by viewModels<FundingParticipateItemViewModel>()
    private val itemDetailDescriptionInfoAdapter = ItemDetailDescriptionInfoAdapter()
    private lateinit var fundingItemInfo : FundingParticipateItemDetailUiModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        fundingParticipateItemViewModel.getFundingParticipateItem(argument.fundingItemId)
    }

    override fun initViewModels() {
        initFundingParticipateItemObserver()
    }

    private fun initItemImgAdapter() {
        val itemAdapter = ItemDetailImgAdapter()
        // 상품 이미지 임시 리스트
        itemAdapter.addItemImgList(fundingItemInfo.itemImgList)
        val imageListSize = fundingItemInfo.itemImgList.size

        binding.tvItemImgPage.text = "1 / $imageListSize"

        with(binding.vpItemImg) {
            adapter = itemAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.tvItemImgPage.text =
                        "${(position % imageListSize) + 1} / $imageListSize"
                }
            })
        }
    }

    private fun initFundingParticipateItemObserver() {
        fundingParticipateItemViewModel.fundingParticipateItem.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initFundingParticipateItemObserver: loading...")
                }
                is ViewState.Success -> {
                    fundingItemInfo = response.value!!
                    binding.fundingItem = fundingItemInfo
                    if(fundingItemInfo.arRegistered) binding.tvIsAr.showAlignTop(makeBalloon())
                    initItemImgAdapter()

                    binding.rvItemInfo.adapter = itemDetailDescriptionInfoAdapter
                    itemDetailDescriptionInfoAdapter.submitList(fundingItemInfo.itemInfo.description)
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initFundingParticipateItemObserver: error... ${response.message}")
                }
            }
        }
    }

    private fun makeBalloon(): Balloon {
        val popUpMessage = Balloon.Builder(requireContext())
            .setWidth(BalloonSizeSpec.WRAP)
            .setHeight(BalloonSizeSpec.WRAP)
            .setText("${argument.userName}님이\n등록한 AR 사진입니다.")
            .setTextColorResource(R.color.white)
            .setTextTypeface(ResourcesCompat.getFont(requireContext(), R.font.notosanskr_medium)!!)
            .setTextSize(13f)
            .setIconHeight(20)
            .setMarginBottom(6)
            .setIconWidth(20)
            .setIconDrawableResource(R.drawable.ic_app_logo)
            .setArrowSize(12)
            .setArrowPosition(0.5f)
            .setPaddingTop(6)
            .setPaddingLeft(10)
            .setPaddingRight(10)
            .setPaddingBottom(6)
            .setCornerRadius(10f)
            .setBackgroundColorResource(R.color.grey)
            .setDismissWhenClicked(true)
            .setDismissWhenOverlayClicked(true)
            .setDismissWhenTouchOutside(true)

        return popUpMessage.build()
    }

    companion object{
        private const val TAG = "FundingParticipateIte"
    }
}