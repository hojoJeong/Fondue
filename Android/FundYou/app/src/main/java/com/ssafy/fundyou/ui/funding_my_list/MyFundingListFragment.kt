package com.ssafy.fundyou.ui.funding_my_list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.activityViewModels
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentMyFundingListBinding
import com.ssafy.fundyou.ui.common.BaseFragment
import com.ssafy.fundyou.ui.funding_my_list.adapter.MyFundingListAdapter

class MyFundingListFragment :
    BaseFragment<FragmentMyFundingListBinding>(R.layout.fragment_my_funding_list) {

    private val myFundingViewModel by activityViewModels<MyFundingListViewModel>()
    private val onGoingFundingListAdapter = MyFundingListAdapter().apply {
        addClickEvent { fundingId ->
            navigate(
                MyFundingListFragmentDirections.actionMyFundingListFragmentToMyFundingFragment(
                    fundingId
                )
            )
        }
    }
    private val closedFundingListAdapter = MyFundingListAdapter().apply {
        addClickEvent { fundingId ->
            navigate(
                MyFundingListFragmentDirections.actionMyFundingListFragmentToMyFundingFragment(
                    fundingId
                )
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        myFundingViewModel.getMyOngoingFunding()
        myFundingViewModel.getMyClosedFundingList()
    }

    override fun initViewModels() {
        initOngoingFundingObserver()
        initClosedFundingObserver()
    }

    private fun initOngoingFundingObserver() {
        myFundingViewModel.ongoingFunding.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initOngoingFundingObserver: loading...")
                }
                is ViewState.Success -> {
                    if (response.value?.isEmpty() == true) {
                        showNoOngoingFundingLayout()
                    } else {
                        onGoingFundingListAdapter.submitList(response.value)
                        binding.rvProgressingFundingList.adapter = onGoingFundingListAdapter
                    }
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initOngoingFundingObserver: error... ${response.message}")
                }
            }
        }
    }


    private fun initClosedFundingObserver() {
        myFundingViewModel.closedFunding.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initOngoingFundingObserver: loading...")
                }
                is ViewState.Success -> {
                    if (response.value?.isEmpty() == true) {
                        with(binding.lyNoClosedFunding) {
                            root.visibility = View.VISIBLE
                            tvNoKeyword.text = "완료된 펀딩이 없습니다."
                        }
                    } else {
                        closedFundingListAdapter.submitList(response.value)
                        binding.rvEndFundingList.adapter = closedFundingListAdapter
                    }
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initOngoingFundingObserver: error... ${response.message}")
                }
            }
        }
    }

    private fun showNoOngoingFundingLayout() {
        val dividerParams =
            binding.divFundingShareBase.layoutParams as ConstraintLayout.LayoutParams
        dividerParams.topToBottom = R.id.ly_no_ongoing_funding
        dividerParams.startToStart = ConstraintSet.PARENT_ID
        dividerParams.endToEnd = ConstraintSet.PARENT_ID
        binding.divFundingShareBase.requestLayout()

        with(binding.lyNoOngoingFunding) {
            root.visibility = View.VISIBLE
            tvNoKeyword.text = "진행 중인 펀딩이 없습니다."
        }
    }

    companion object {
        private const val TAG = "MyFundingListFragment..."
    }
}