package com.ssafy.fundyou.ui.funding_my

import android.os.Bundle
import android.view.View
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentMyFundingParticipationBinding
import com.ssafy.fundyou.ui.common.BaseFragment
import com.ssafy.fundyou.ui.funding_my.adapter.FundingParticipateAdapter
import com.ssafy.fundyou.ui.funding_my.model.FundingParticipateModel

class FundingParticipationFragment :
    BaseFragment<FragmentMyFundingParticipationBinding>(R.layout.fragment_my_funding_participation) {

    private val fundingParticipateAdapter = FundingParticipateAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        initMessageList()
    }

    override fun initViewModels() {

    }

    private fun initMessageList() {
        val tempList = listOf(
            FundingParticipateModel(0, "축하해~", "정호조", 1000),
            FundingParticipateModel(1, "123333333333333333333333333333333333333333333333333333333333333333333333~", "정1호조", 1000),
            FundingParticipateModel(
                2, "축하해~", "정호1조", 33333
            )
        )
        fundingParticipateAdapter.submitList(tempList)
        binding.rvItemFundingParticipation.adapter = fundingParticipateAdapter
    }
}