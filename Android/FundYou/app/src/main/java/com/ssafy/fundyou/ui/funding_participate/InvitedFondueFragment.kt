package com.ssafy.fundyou.ui.funding_participate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentInvitedFondueBinding
import com.ssafy.fundyou.databinding.ItemInvitedFondueBinding
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.ui.funding_participate.model.InvitedFundingModel

class InvitedFondueFragment : BaseFragment<FragmentInvitedFondueBinding>(R.layout.fragment_invited_fondue) {
    private val itemList = mutableListOf<InvitedFundingModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    override fun initView() {
        initInvitedFondueCategory()
        initInvitedFondueList()
    }

    override fun initViewModels() {
    }

    private fun initInvitedFondueCategory(){

    }

    private fun initInvitedFondueList(){
        //TODO(임시 데이터 추가)
        with(itemList){
            add(InvitedFundingModel(0, "김싸피", true,0 ,1000))
        }
        val invitedFondueAdapter = InvitedFondueItemAdapter()
        invitedFondueAdapter.submitList()
        binding.rvInvitedFondueItem.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

    }
}