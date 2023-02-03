package com.ssafy.fundyou.ui.funding_paticipation

import android.os.Bundle
import android.view.View
import com.ssafy.fundyou.R

import com.ssafy.fundyou.databinding.FragmentFundingParticipationBinding
import com.ssafy.fundyou.ui.base.BaseFragment

class FundingParticipationFragment :
    BaseFragment<FragmentFundingParticipationBinding>(R.layout.fragment_funding_participation) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {

    }

    override fun initViewModels() {

    }
}