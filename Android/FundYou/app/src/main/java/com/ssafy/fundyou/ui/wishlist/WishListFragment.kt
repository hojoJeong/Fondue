package com.ssafy.fundyou.ui.wishlist

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.util.Pair
import androidx.fragment.app.activityViewModels
import com.google.android.material.datepicker.MaterialDatePicker
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.showAlignTop
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentWishListBinding
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.ui.wishlist.model.WishListModel
import com.ssafy.fundyou.util.addComma
import java.text.SimpleDateFormat
import java.util.*

class WishListFragment : BaseFragment<FragmentWishListBinding>(R.layout.fragment_wish_list) {

    private lateinit var calendar: MaterialDatePicker<Pair<Long, Long>>
    private val wishListViewModel by activityViewModels<WishListViewModel>()
    private val wishListAdapter = WishListAdapter().apply {
        deleteItemBtnClickListener { id ->
            wishListViewModel.deleteWishListItem(id)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        binding.btnWishlistStartFunding.showAlignTop(makeBalloon())
        wishListViewModel.getWishListItemList()
    }

    override fun initViewModels() {
        wishListItemObserve()
        initWishListItemDeleteResultObserve()
    }


    private fun wishListItemObserve() {
        wishListViewModel.wishListItem.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    binding.lyEmptyWishList.root.visibility = View.GONE
                    binding.btnWishlistStartFunding.visibility = View.GONE
                    binding.scvWishlist.visibility = View.GONE
                }
                is ViewState.Success -> {
                    if (response.value?.isNotEmpty() == true) {
                        binding.scvWishlist.visibility = View.VISIBLE
                        binding.btnWishlistStartFunding.visibility = View.VISIBLE

                        initWishListInfo(response.value!!)
                        startFundingBtnListener()
                        initCalendar()
                        setFundingPeriod()
                    } else binding.lyEmptyWishList.root.visibility = View.VISIBLE
                    Log.d(TAG, "wishListItemObserve: WishList Item Loading Success ${response.value}")
                }
                is ViewState.Error -> {
                    Log.d(
                        TAG,
                        "wishListItemObserve: WishList Item Loading Error... ${response.message}"
                    )
                }
            }
        }
    }

    private fun initWishListItemDeleteResultObserve() {
        wishListViewModel.resultWishList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(
                        TAG,
                        "initWishListItemChangeResultObserve: WishList Item Delete Loading..."
                    )
                }
                is ViewState.Success -> {
                    wishListViewModel.getWishListItemList()
                }
                is ViewState.Error -> {
                    Log.d(
                        TAG,
                        "initWishListItemChangeResultObserve: WishList Item Delete Error...${response.message}"
                    )
                }
            }
        }
    }

    private fun initWishListInfo(itemList: List<WishListModel>) {
        binding.rvWishlistItem.adapter = wishListAdapter
        wishListAdapter.submitList(itemList)

        var totalCount = 0
        var totalPrice = 0

        itemList.forEach { data ->
            totalCount += data.count
            totalPrice += data.price * data.count
        }
        with(binding) {
            tvWishlistInfoPrice.text = "${addComma(totalPrice)}원"
            tvWishlistItemCount.text = "${totalCount}개"
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

    private fun initCalendar() {
        binding.tvWishlistFundingPeriod.text = "날짜를 지정해주세요"

        calendar = MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText(R.string.title_wishlist_callendar)
            .setPositiveButtonText("확인")
            .setNegativeButtonText("취소")
            .setSelection(
                Pair(
                    MaterialDatePicker.todayInUtcMilliseconds(),
                    MaterialDatePicker.todayInUtcMilliseconds()
                )
            )
            .setTheme(R.style.custom_calendar)
            .build()

        binding.btnWishlistFundingPeriod.setOnClickListener {
            calendar.show(parentFragmentManager, "펀딩 기간 설정")
        }
    }

    private fun setFundingPeriod() {
        calendar.addOnPositiveButtonClickListener {
            val startPick = calendar.selection?.first
            val endPick = calendar.selection?.second

            val dateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
            val startDay = dateFormat.format(startPick)
            val endDay = dateFormat.format(endPick)

            binding.tvWishlistFundingPeriod.text =
                getString(R.string.content_wishlist_funding_period, startDay, endDay)
        }
    }


    private fun startFundingBtnListener() {
        binding.btnWishlistStartFunding.setOnClickListener {
            navigate(WishListFragmentDirections.actionWishListFragmentToMyFundingFragment())
        }
    }

}