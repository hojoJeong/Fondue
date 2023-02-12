package com.ssafy.fundyou.ui.funding_my

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentMyFundingBinding
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.ui.funding_my.adapter.MyFundingItemListAdapter
import com.ssafy.fundyou.ui.funding_my.model.MyFundingInfoUiModel
import com.ssafy.fundyou.ui.funding_my.model.MyFundingItemListUiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFundingFragment : BaseFragment<FragmentMyFundingBinding>(R.layout.fragment_my_funding) {

    private val ongoingFundingItemAdapter = MyFundingItemListAdapter()
    private val closedFundingItemAdapter = MyFundingItemListAdapter()

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
        ongoingFundingItemAdapter.submitList(myFundingItem.myFundingOngoingList)
        binding.rvProgressingFundingList.adapter = ongoingFundingItemAdapter
    }

    private fun initClosedFundingItemList() {
        if (myFundingItem.myFundingClosedList.isEmpty()) {
            with(binding) {
                tvEndFundingTitle.visibility = View.GONE
                rvEndFundingList.visibility = View.GONE
            }
        } else {
            closedFundingItemAdapter.submitList(myFundingItem.myFundingClosedList)
            binding.rvEndFundingList.adapter = closedFundingItemAdapter
        }
    }

    private fun addMyFundingDetailEvent() {
        binding.tvFundingDetail.setOnClickListener {
            navigate(MyFundingFragmentDirections.actionMyFundingFragmentToFundingDetailFragment())
        }
    }


    companion object {
        private const val TAG = "MyFundingFragment..."
    }
}