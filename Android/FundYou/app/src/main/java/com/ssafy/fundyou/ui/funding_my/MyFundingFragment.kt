package com.ssafy.fundyou.ui.funding_my

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentMyFundingBinding
import com.ssafy.fundyou.ui.common.BaseFragment
import com.ssafy.fundyou.ui.common.KakaoMessageTool
import com.ssafy.fundyou.ui.common.dialog.CommonDialog
import com.ssafy.fundyou.ui.funding_my.adapter.MyFundingItemListAdapter
import com.ssafy.fundyou.ui.funding_my.model.MyFundingInfoUiModel
import com.ssafy.fundyou.ui.funding_my.model.MyFundingItemListUiModel
import com.ssafy.fundyou.ui.mypage.MyPageViewModel
import com.ssafy.fundyou.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFundingFragment : BaseFragment<FragmentMyFundingBinding>(R.layout.fragment_my_funding) {

    private val ongoingFundingItemAdapter = MyFundingItemListAdapter().apply {
        addClickEvent { fundingItemId, status ->
            terminateFundingItemEvent(fundingItemId, status)
        }
        addArButtonClickEvent { fundingItemId ->
            navigate(MyFundingFragmentDirections.actionMyFundingFragmentToArGalleryFragment(fundingItemId))
        }
    }
    private val closedFundingItemAdapter = MyFundingItemListAdapter()
    private val myPageViewModel by viewModels<MyPageViewModel>()
    private val args by navArgs<MyFundingFragmentArgs>()
    private val myFundingViewModel by viewModels<MyFundingViewModel>()
    private lateinit var myFundingInfo: MyFundingInfoUiModel
    private lateinit var myFundingItem: MyFundingItemListUiModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        myFundingViewModel.getFundingInfo(args.fundingId)
        addMyFundingDetailEvent()
    }

    override fun initViewModels() {
        initMyFundingInfObserver()
        initMyFundingItemListObserver()
        initTerminateFundingItemObserver()
        initMyPageUserInfoObserver()
    }

    private fun terminateFundingItemEvent(id: Long, status: Boolean) {
        if (!status) myFundingViewModel.terminateFundingItem(id)
        else showTerminateFundingItemDialog(
            id,
            title = "펀딩을 중단하시겠어요?",
            content = "이미 펀딩된 금액만 포인트로 들어와요"
        )
    }

    private fun showTerminateFundingItemDialog(id: Long, title: String, content: String) {
        val dialog = CommonDialog(requireContext()).apply {
            initDialog(id, title, content) { fundingItemId ->
                myFundingViewModel.terminateFundingItem(fundingItemId)
            }
        }
        dialog.show()
        dialog.setCanceledOnTouchOutside(true)
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun initTerminateFundingItemObserver() {
        myFundingViewModel.terminateFundingItemStatus.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initTerminateFundingItemObserver: loading...")
                }
                is ViewState.Success -> {
                    requireContext().showToast("펀딩이 완료되었습니다.")
                    myFundingViewModel.getFundingInfo(args.fundingId)
                    myFundingViewModel.getFundingItemList(args.fundingId)
                }
                is ViewState.Error -> {

                }
            }
        }
    }

    private fun initMyPageUserInfoObserver() {
        myPageViewModel.userInfo.observe(viewLifecycleOwner) { response ->
            when(response){
                is ViewState.Loading ->{
                    Log.d(TAG, "initMyPageUserInfoObserver: loading...")
                }
                is ViewState.Success -> {
                    shareFundingBtnClickListener()
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initMyPageUserInfoObserver: error ${response.message}")
                }
            }
        }
    }

    private fun initMyFundingInfObserver() {
        myFundingViewModel.myFundingInfo.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initMyFundingInfObserver: ")
                }
                is ViewState.Success -> {
                    myFundingInfo = response.value!!
                    binding.fundingInfo = myFundingInfo
                    myFundingViewModel.getFundingItemList(args.fundingId)
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initMyFundingInfObserver: error... ${response.message}")
                }
            }
        }
    }

    private fun initMyFundingItemListObserver() {
        myFundingViewModel.myFundingItem.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initMyFundingItemListObserver: loading...")
                }
                is ViewState.Success -> {
                    myFundingItem = response.value!!
                    initOngoingFundingItemList()
                    initClosedFundingItemList()
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initMyFundingItemListObserver: error... ${response.message}")
                }
            }
        }
    }

    private fun initOngoingFundingItemList() {
        myPageViewModel.getUserInfo()
        if (myFundingItem.myFundingOngoingList.isEmpty()) {
            with(binding) {
                tvProgressingFundingTitle.visibility = View.GONE
                rvProgressingFundingList.visibility = View.GONE
            }
        } else {
            binding.tvProgressingFundingTitle.visibility = View.VISIBLE
            binding.rvProgressingFundingList.visibility = View.VISIBLE
            ongoingFundingItemAdapter.submitList(null)
            ongoingFundingItemAdapter.submitList(myFundingItem.myFundingOngoingList)
            binding.rvProgressingFundingList.adapter = ongoingFundingItemAdapter
        }
    }

    private fun initClosedFundingItemList() {
        if (myFundingItem.myFundingClosedList.isEmpty()) {
            with(binding) {
                tvEndFundingTitle.visibility = View.GONE
                rvEndFundingList.visibility = View.GONE
            }
        } else {
            binding.tvEndFundingTitle.visibility = View.VISIBLE
            binding.rvEndFundingList.visibility = View.VISIBLE
            closedFundingItemAdapter.submitList(null)
            closedFundingItemAdapter.submitList(myFundingItem.myFundingClosedList)
            binding.rvEndFundingList.adapter = closedFundingItemAdapter
        }
    }

    private fun addMyFundingDetailEvent() {
        binding.tvFundingDetail.setOnClickListener {
            navigate(
                MyFundingFragmentDirections.actionMyFundingFragmentToMyFundingDetailFragment(
                    args.fundingId
                )
            )
        }
    }

    private fun shareFundingBtnClickListener() {
        val kakaoMessageTool = KakaoMessageTool(requireContext())
        val myFundingInfo = myFundingViewModel.myFundingInfo.value?.value
        val myFundingImage =
            myFundingViewModel.myFundingItem.value?.value?.myFundingOngoingList!![0].img

        binding.ivFundingShare.setOnClickListener {
            val feed = kakaoMessageTool.makeFeed(
                "${myPageViewModel.userInfo.value?.value?.userName}님의 퐁듀를 확인해보세요!",
                "D-${myFundingInfo?.deadLine}",
                myFundingImage,
                "상품 확인해보기",
                "funding_id", myFundingInfo?.id!!
            )
            kakaoMessageTool.sendKakaoLink(feed)
        }
    }


    companion object {
        private const val TAG = "MyFundingFragment..."
    }
}