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
    private val currentCategory = ""
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
            clear()
            add(InvitedFundingModel(0, "김싸피", true, false, "2023.01.01 ~ 2023.02.01",1000))
            add(InvitedFundingModel(0, "김싸피", false, false, "2023.01.01 ~ 2023.02.01",1000))
            add(InvitedFundingModel(0, "김싸피", true, true, "2023.01.01 ~ 2023.02.01",1000))
            add(InvitedFundingModel(0, "김싸피", false, true, "2023.01.01 ~ 2023.02.01",1000))
            add(InvitedFundingModel(0, "김싸피", true, false, "2023.01.01 ~ 2023.02.01",1000))
            add(InvitedFundingModel(0, "김싸피", false, false, "2023.01.01 ~ 2023.02.01",1000))
            add(InvitedFundingModel(0, "김싸피", true, true, "2023.01.01 ~ 2023.02.01",1000))
            add(InvitedFundingModel(0, "김싸피", false, true, "2023.01.01 ~ 2023.02.01",1000))
            add(InvitedFundingModel(0, "김싸피", true, false, "2023.01.01 ~ 2023.02.01",1000))
            add(InvitedFundingModel(0, "김싸피", false, false, "2023.01.01 ~ 2023.02.01",1000))
            add(InvitedFundingModel(0, "김싸피", true, true, "2023.01.01 ~ 2023.02.01",1000))
            add(InvitedFundingModel(0, "김싸피", false, true, "2023.01.01 ~ 2023.02.01",1000))

        }

        val invitedFondueAdapter = InvitedFondueItemAdapter()
        invitedFondueAdapter.submitList(itemList)
        with(binding.rvInvitedFondueItem){
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            adapter = invitedFondueAdapter
        }

    }

}