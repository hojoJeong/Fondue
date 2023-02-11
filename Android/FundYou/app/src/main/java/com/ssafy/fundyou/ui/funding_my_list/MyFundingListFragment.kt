package com.ssafy.fundyou.ui.funding_my_list

import android.os.Bundle
import android.view.View
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentMyFundingListBinding
import com.ssafy.fundyou.ui.base.BaseFragment

class MyFundingListFragment : BaseFragment<FragmentMyFundingListBinding>(R.layout.fragment_my_funding_list){

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