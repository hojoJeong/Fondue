package com.ssafy.fundyou.ui.funding_invited_list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentInvitedFondueListBinding
import com.ssafy.fundyou.ui.common.BaseFragment
import com.ssafy.fundyou.ui.funding_participate.FundingParticipateViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InvitedFondueListFragment : BaseFragment<FragmentInvitedFondueListBinding>(R.layout.fragment_invited_fondue_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {

    }

    override fun initViewModels() {

    }

    companion object{
        private const val TAG = "InvitedFondueListFragme"
    }
}