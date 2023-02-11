package com.ssafy.fundyou.ui.funding_my_list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentMyFundingListBinding
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.ui.funding_my_list.adapter.MyFundingListAdapter

class MyFundingListFragment : BaseFragment<FragmentMyFundingListBinding>(R.layout.fragment_my_funding_list) {

    private val myFundingViewModel by activityViewModels<MyFundingListViewModel>()
    private val onGoingFundingListAdapter = MyFundingListAdapter()
    private val closedFundingListAdapter = MyFundingListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        myFundingViewModel.getMyOngoingFunding()
    }

    override fun initViewModels() {
        initOngoingFundingObserver()
    }

    private fun initOngoingFundingObserver() {
        myFundingViewModel.ongoingFunding.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initOngoingFundingObserver: loading...")
                }
                is ViewState.Success -> {
                    onGoingFundingListAdapter.submitList(response.value)
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initOngoingFundingObserver: erorr... ${response.message}")
                }
            }
        }
    }

    companion object {
        private const val TAG = "MyFundingListFragment..."
    }
}