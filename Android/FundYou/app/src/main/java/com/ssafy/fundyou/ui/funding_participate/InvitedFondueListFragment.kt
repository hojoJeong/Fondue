package com.ssafy.fundyou.ui.funding_participate

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentInvitedFondueListBinding
import com.ssafy.fundyou.ui.common.BaseFragment
import com.ssafy.fundyou.ui.funding_participate.model.InvitedFundingListModel

class InvitedFondueListFragment : BaseFragment<FragmentInvitedFondueListBinding>(R.layout.fragment_invited_fondue_list) {
    private val itemList = mutableListOf<InvitedFundingListModel>()
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
            add(InvitedFundingListModel(0, "김싸피", true, false, "2023.01.01 ~ 2023.02.01",1000))
            add(InvitedFundingListModel(0, "김싸피", false, false, "2023.01.01 ~ 2023.02.01",1000))
            add(InvitedFundingListModel(0, "김싸피", true, true, "2023.01.01 ~ 2023.02.01",1000))
            add(InvitedFundingListModel(0, "김싸피", false, true, "2023.01.01 ~ 2023.02.01",1000))
            add(InvitedFundingListModel(0, "김싸피", true, false, "2023.01.01 ~ 2023.02.01",1000))
            add(InvitedFundingListModel(0, "김싸피", false, false, "2023.01.01 ~ 2023.02.01",1000))
            add(InvitedFundingListModel(0, "김싸피", true, true, "2023.01.01 ~ 2023.02.01",1000))
            add(InvitedFundingListModel(0, "김싸피", false, true, "2023.01.01 ~ 2023.02.01",1000))
            add(InvitedFundingListModel(0, "김싸피", true, false, "2023.01.01 ~ 2023.02.01",1000))
            add(InvitedFundingListModel(0, "김싸피", false, false, "2023.01.01 ~ 2023.02.01",1000))
            add(InvitedFundingListModel(0, "김싸피", true, true, "2023.01.01 ~ 2023.02.01",1000))
            add(InvitedFundingListModel(0, "김싸피", false, true, "2023.01.01 ~ 2023.02.01",1000))

        }

        val invitedFondueAdapter = InvitedFondueListItemAdapter()
        invitedFondueAdapter.submitList(itemList)
        invitedFondueAdapter.fundingItemClickListener { item ->
            navigate(InvitedFondueListFragmentDirections.actionInvitedFondueListFragmentToInvitedFondueFragment(0))
        }
        with(binding.rvInvitedFondueItemList){
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            adapter = invitedFondueAdapter
        }

    }

}