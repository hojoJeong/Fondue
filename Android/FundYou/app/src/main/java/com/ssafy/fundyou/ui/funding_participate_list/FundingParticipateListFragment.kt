package com.ssafy.fundyou.ui.funding_invited_list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentInvitedFondueListBinding
import com.ssafy.fundyou.ui.common.BaseFragment
import com.ssafy.fundyou.ui.funding_invited_list.adapter.FundingParticipateListItemAdapter
import com.ssafy.fundyou.ui.funding_invited_list.model.FundingParticipateListUiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InvitedFondueListFragment :
    BaseFragment<FragmentInvitedFondueListBinding>(R.layout.fragment_invited_fondue_list) {
    private val fundingViewModel by activityViewModels<FundingParticipateListViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        fundingViewModel.getFundingParticipateOngoingList()
        fundingViewModel.getFundingParticipateDoneList()
    }

    override fun initViewModels() {
        initFundingParticipateOngoingListItemObserve()
        initFundingParticipateDoneListItemObserve()
    }

    private fun initFundingParticipateOngoingListItemObserve() {
        fundingViewModel.invitedFundingOngoingList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(
                        TAG,
                        "initFundingParticipateListItemObserve: Get Funding Participate Ongoing List Loading..."
                    )
                }
                is ViewState.Success -> {
                    if (response.value?.isEmpty()!!) {
                        binding.lyFundingParticipateNoOngoingItem.tvNoKeyword.text =
                            "진행 중인 퐁듀가 없습니다."
                        binding.lyFundingParticipateNoOngoingItem.root.visibility = View.VISIBLE
                    } else {
                        binding.lyFundingParticipateNoOngoingItem.root.visibility = View.GONE
                        initFundingListAdapter(true, response.value ?: emptyList())
                    }
                }
                is ViewState.Error -> {
                    Log.d(
                        TAG,
                        "initFundingParticipateListItemObserve: Get Funding Participate Ongoing List Error...${response.message}"
                    )
                }
            }
        }
    }

    private fun initFundingParticipateDoneListItemObserve() {
        fundingViewModel.invitedFundingDoneList.observe(viewLifecycleOwner) { response ->
            when (response) {

                is ViewState.Loading -> {
                    Log.d(
                        TAG,
                        "initFundingParticipateListItemObserve: Get Funding Participate Done List Loading..."
                    )
                }
                is ViewState.Success -> {
                    if (response.value?.isEmpty()!!) {
                        binding.lyFundingParticipateNoDoneItem.tvNoKeyword.text = "종료된 퐁듀가 없습니다."
                        binding.lyFundingParticipateNoDoneItem.root.visibility = View.VISIBLE
                    } else {
                        binding.lyFundingParticipateNoDoneItem.root.visibility = View.GONE
                        initFundingListAdapter(false, response.value ?: emptyList())
                    }
                }
                is ViewState.Error -> {
                    Log.d(
                        TAG,
                        "initFundingParticipateListItemObserve: Get Funding Participate Done List Error...${response.message}"
                    )
                }
            }
        }
    }

    private fun initFundingListAdapter(ongoing: Boolean, fundingList: List<FundingParticipateListUiModel>) {
        val fundingAdapter = FundingParticipateListItemAdapter().apply {
            submitList(fundingList)
            fundingItemClickListener { id ->
                navigate(
                    InvitedFondueListFragmentDirections.actionInvitedFondueListFragmentToInvitedFondueFragment(
                        id
                    )
                )
            }
        }
        when(ongoing){
            true -> {
                with(binding.rvInvitedFondueItemListOngoing) {
                    layoutManager =
                        GridLayoutManager(requireContext(), SPAN_COUNT, GridLayoutManager.VERTICAL, false)
                    adapter = fundingAdapter
                }
            }
            false -> {
                with(binding.rvFundingParticipateItemListDone) {
                    layoutManager =
                        GridLayoutManager(requireContext(), SPAN_COUNT, GridLayoutManager.VERTICAL, false)
                    adapter = fundingAdapter
                }
            }
        }

    }


    companion object {
        private const val TAG = "InvitedFondueListFragme"
        private const val SPAN_COUNT = 2
    }
}