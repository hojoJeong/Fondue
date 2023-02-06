package com.ssafy.fundyou.ui.wishlist

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.showAlignTop
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentWishListBinding
import com.ssafy.fundyou.domain.model.ProductItemModel
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.ui.home.adapter.ProductItemAdapter
import java.text.SimpleDateFormat

class WishListFragment : BaseFragment<FragmentWishListBinding>(R.layout.fragment_wish_list) {
    private val wishLIstItemList = mutableListOf<ProductItemModel>()
    private lateinit var calendar:  MaterialDatePicker<Pair<Long, Long>>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    override fun initView() {
        binding.btnWishlistStartFunding.showAlignTop(makeBalloon())
        initWishListItem()
        startFundingBtnListener()
        cancelBtnListener()
        initCalendar()
        setFundingPeriod()
    }

    override fun initViewModels() {
    }

    private fun initWishListItem(){
        with(wishLIstItemList) {
            add(ProductItemModel(0, 100000, "", "BESPOKE 냉장고", false, "삼성", true))
            add(ProductItemModel(1, 100000, "", "BESPOKE 냉장고", true, "삼성", false))
            add(ProductItemModel(2, 100000, "", "BESPOKE 냉장고", false, "삼성", false))
            add(ProductItemModel(3, 100000, "", "BESPOKE 냉장고", true, "삼성", true))
            add(ProductItemModel(4, 100000, "", "BESPOKE 냉장고", false, "삼성", true))
            add(ProductItemModel(5, 100000, "", "BESPOKE 냉장고", true, "삼성", false))
        }

        val rvWishList = binding.rvWishlistItem
        val wishListItemAdapter = ProductItemAdapter()
        with(wishListItemAdapter){
            setFavoriteVisibility(false)
            checkNeedRanking(false)
            submitList(wishLIstItemList)
            rvWishList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvWishList.adapter = wishListItemAdapter
        }
    }

    private fun makeBalloon(): Balloon {
        val popUpMessage = Balloon.Builder(requireContext())
            .setWidth(BalloonSizeSpec.WRAP)
            .setHeight(BalloonSizeSpec.WRAP)
            .setText(resources.getString(R.string.content_wishlist_balloon))
            .setTextColorResource(R.color.white)
            .setTextTypeface(ResourcesCompat.getFont(requireContext(), R.font.notosanskr_medium)!!)
            .setTextSize(13f)
            .setMarginBottom(6)
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

    private fun initCalendar(){
        calendar = MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText(R.string.title_wishlist_callendar)
            .setPositiveButtonText("확인")
            .setNegativeButtonText("취소")
            .setSelection(Pair(MaterialDatePicker.todayInUtcMilliseconds(), MaterialDatePicker.todayInUtcMilliseconds()))
            .setTheme(R.style.custom_calendar)
            .build()
        binding.btnWishlistFundingPeriod.setOnClickListener {
            calendar.show(parentFragmentManager, "펀딩 기간 설정")
        }
    }

    private fun setFundingPeriod(){
        calendar.addOnPositiveButtonClickListener {
            val startPick = calendar.selection?.first
            val endPick = calendar.selection?.second

            val dateFormat = SimpleDateFormat("yyyy.MM.dd")
            val startDay = dateFormat.format(startPick)
            val endDay = dateFormat.format(endPick)

            binding.tvWishlistFundingPeriod.text = "$startDay ~ $endDay"
        }
    }

    private fun startFundingBtnListener(){
        binding.btnWishlistStartFunding.setOnClickListener {
            navigate(WishListFragmentDirections.actionWishListFragmentToMyFundingFragment())
        }
    }

    private fun cancelBtnListener(){
        binding.btnWishlistCancel.setOnClickListener {
            popBackStack()
        }
    }
}