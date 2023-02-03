package com.ssafy.fundyou.ui.funding_participation

import android.os.Bundle
import android.view.View
import com.ssafy.fundyou.R

import com.ssafy.fundyou.databinding.FragmentFundingParticipationBinding
import com.ssafy.fundyou.domain.model.ProductItemModel
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.ui.funding_my.model.FundingItemModel
import com.ssafy.fundyou.ui.funding_participation.adapter.FundingParticipateAdapter
import com.ssafy.fundyou.ui.funding_participation.model.FundingParticipateModel

class FundingParticipationFragment :
    BaseFragment<FragmentFundingParticipationBinding>(R.layout.fragment_funding_participation) {

    private val fundingParticipateAdapter = FundingParticipateAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        initMessageList()
        binding.item = FundingItemModel(
            id = 0,
            ProductItemModel(id = 1, price = 3000, "img", "상품이름1", false, "브랜드1", true),
            tempProductImg = R.drawable.ic_launcher_background,
            currentFundingPrice = 1000,
            fundingParticipate = 3,
        )
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