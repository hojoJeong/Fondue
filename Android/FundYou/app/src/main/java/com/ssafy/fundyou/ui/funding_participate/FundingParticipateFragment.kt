package com.ssafy.fundyou.ui.funding_participate

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentFundingParticipateBinding
import com.ssafy.fundyou.ui.common.BaseFragment
import com.ssafy.fundyou.ui.funding_participate.adapter.FundingParticipateItemAdapter

class FundingParticipateFragment :
    BaseFragment<FragmentFundingParticipateBinding>(R.layout.fragment_funding_participate) {

    private val fundingParticipateViewModel by viewModels<FundingParticipateViewModel>()
    private val argument by navArgs<FundingParticipateFragmentArgs>()
    private lateinit var userName : String
    private val fundingItemAdapter = FundingParticipateItemAdapter().apply {
        addFundingButtonEvent { fundingItemId ->
            FundingParticipateFragmentDirections.actionFundingParticipateFragmentToFundingParticipateItemFragment(fundingItemId, userName)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        fundingParticipateViewModel.getFundingHostInfo(argument.fundingId)
        fundingParticipateViewModel.getFundingItemList(argument.fundingId)
    }

    override fun initViewModels() {
        initFundingParticipateItemListObserver()
        initFundingParticipateHostInfoObserver()
    }

    private fun initFundingParticipateHostInfoObserver() {
        fundingParticipateViewModel.fundingHostInfo.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initFundingParticipateHostInfoObserver: loading...")
                }
                is ViewState.Success -> {
                    binding.fundingHostInfo = response.value
                    userName = response.value?.userName ?: ""
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initFundingParticipateHostInfoObserver: error ${response.message}")
                }
            }
        }
    }

    private fun initFundingParticipateItemListObserver() {
        fundingParticipateViewModel.fundingItemList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initFundingParticipateItemListObserver: loading...")
                }
                is ViewState.Success -> {
                    fundingItemAdapter.submitList(response.value)
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initFundingParticipateItemListObserver: error ${response.message}")
                }
            }
        }
    }

    companion object {
        private const val TAG = "FundingParticipateFragm"
    }
}