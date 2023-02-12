package com.ssafy.fundyou.ui.wishlist

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.util.Pair
import androidx.fragment.app.activityViewModels
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
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
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class WishListFragment : BaseFragment<FragmentWishListBinding>(R.layout.fragment_wish_list) {

    private lateinit var calendar: MaterialDatePicker<Pair<Long, Long>>
    private val wishListViewModel by activityViewModels<WishListViewModel>()
    private val wishListAdapter = WishListAdapter().apply {
        deleteItemBtnClickListener { id ->
            wishListViewModel.deleteWishListItem(id)
        }
    }
    private var endTime: Long = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        wishListViewModel.getWishListItemList()
    }

    override fun initViewModels() {
        wishListItemObserve()
        initWishListItemDeleteResultObserve()
        initAddFundingResultObserver()
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
                        binding.btnWishlistStartFunding.showAlignTop(
                            makeBalloon(
                                resources.getString(
                                    R.string.content_wishlist_balloon
                                )
                            )
                        )

                        initWishListInfo(response.value!!)
                        addFundingStartBtnListener()
                        initCalendar()
                        setFundingPeriod()
                    } else binding.lyEmptyWishList.root.visibility = View.VISIBLE
                    Log.d(
                        TAG,
                        "wishListItemObserve: WishList Item Loading Success ${response.value}"
                    )
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

    private fun initAddFundingResultObserver() {
        wishListViewModel.addFundingStatus.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initAddFundingResultObserver: loading...")
                }
                is ViewState.Success -> {
                    // 내 펀딩 화면으로 이동
                    val fundingId = response.value!!
                    navigate(
                        WishListFragmentDirections.actionWishListFragmentToMyFundingFragment(
                            fundingId
                        )
                    )
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initAddFundingResultObserver: error... ${response.message}")
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

        var totalPrice = 0

        itemList.forEach { data ->
            totalPrice += data.price * data.count
        }
        with(binding) {
            tvWishlistInfoPrice.text = "${addComma(totalPrice)}원"
            tvWishlistItemCount.text = "${itemList.size}개"
        }
    }

    private fun makeBalloon(message: String): Balloon {
        val popUpMessage = Balloon.Builder(requireContext())
            .setWidth(BalloonSizeSpec.WRAP)
            .setHeight(BalloonSizeSpec.WRAP)
            .setText(message)
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

        val calenderConstraint =
            CalendarConstraints.Builder().setValidator(DateValidatorPointForward.now()).build()

        calendar = MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText(R.string.title_wishlist_calendar)
            .setPositiveButtonText("확인")
            .setNegativeButtonText("취소")
            .setCalendarConstraints(calenderConstraint)
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
            endTime = calendar.selection?.second ?: 0

            val dateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
            val startDay = dateFormat.format(startPick)
            val endDay = dateFormat.format(endTime)

            binding.tvWishlistFundingPeriod.text =
                getString(R.string.content_wishlist_funding_period, startDay, endDay)
        }
    }


    private fun addFundingStartBtnListener() {
        binding.btnWishlistStartFunding.setOnClickListener {
            if (binding.editFundingName.text.toString() == "") binding.editFundingName.showAlignTop(
                makeBalloon("펀딩 제목을 입력해주세요!")
            )
            else if (endTime == 0L) binding.btnWishlistFundingPeriod.showAlignTop(makeBalloon("기간을 설정해주세요!"))
            else wishListViewModel.addFunding(endTime, binding.editFundingName.text.toString())
        }
    }
}