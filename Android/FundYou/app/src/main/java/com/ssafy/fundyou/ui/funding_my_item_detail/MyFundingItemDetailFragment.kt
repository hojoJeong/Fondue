package com.ssafy.fundyou.ui.funding_my_item_detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentMyItemDetailFragmentBinding
import com.ssafy.fundyou.ui.common.BaseFragment
import com.ssafy.fundyou.ui.funding_my_item_detail.adapter.MyFundingItemMessageAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFundingItemDetailFragment :
    BaseFragment<FragmentMyItemDetailFragmentBinding>(R.layout.fragment_my_item_detail_fragment) {

    private val myFundingItemDetailViewModel by viewModels<MyFundingItemDetailViewModel>()
    private val args by navArgs<MyFundingItemDetailFragmentArgs>()
    private val myFundingMessageListAdapter = MyFundingItemMessageAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        binding.rvItemFundingParticipation.adapter = myFundingMessageListAdapter
        myFundingItemDetailViewModel.getFundingItemInfo(args.fundingItemId)
        myFundingItemDetailViewModel.getFundingParticipateMessageList(args.fundingItemId)
    }

    override fun initViewModels() {
        initFundingInfoObserver()
        initFundingMessageListObserver()
    }

    private fun initFundingInfoObserver() {
        myFundingItemDetailViewModel.fundingItem.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initFundingInfoObserver: loading...")
                }
                is ViewState.Success -> {
                    binding.fundingItemInfo = response.value
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initFundingInfoObserver: error... ${response.message}")
                }
            }
        }
    }

    private fun initFundingMessageListObserver() {
        myFundingItemDetailViewModel.fundingParticipateMessageList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initFundingMessageListObserver: loading...")
                }
                is ViewState.Success -> {
                    if (response.value?.isEmpty() == true) {
                        with(binding) {
                            lyNoParticipate.root.visibility = View.VISIBLE
                            lyNoParticipate.tvNoKeyword.text = "펀딩에 참여한 사용자가 없습니다."
                            tvItemFundingParticipateStateTitle.visibility = View.GONE
                            rvItemFundingParticipation.visibility = View.GONE
                            tvItemFundingParticipation.visibility = View.GONE
                        }
                    } else {
                        with(binding){
                            rvItemFundingParticipation.visibility = View.VISIBLE
                            tvItemFundingParticipateStateTitle.visibility = View.VISIBLE
                            lyNoParticipate.root.visibility = View.GONE
                            tvItemFundingParticipation.visibility = View.VISIBLE
                        }
                        myFundingMessageListAdapter.submitList(null)
                        myFundingMessageListAdapter.submitList(response.value)
                    }
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initFundingMessageListObserver: error... ${response.message}")
                }
            }
        }
    }

    companion object {
        private const val TAG = "MyFundingItemDetail..."
    }
}