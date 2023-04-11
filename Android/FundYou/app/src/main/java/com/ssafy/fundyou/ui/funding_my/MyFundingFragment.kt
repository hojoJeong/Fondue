package com.ssafy.fundyou.ui.funding_my

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentMyFundingBinding
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.ui.funding_my.adapter.MyFundingItemEndListAdapter
import com.ssafy.fundyou.ui.funding_my.adapter.MyFundingProcessingListAdapter

class MyFundingFragment : BaseFragment<FragmentMyFundingBinding>(R.layout.fragment_my_funding) {

    private val processingFundingAdapter = MyFundingProcessingListAdapter()
    private val endFundingAdapter = MyFundingItemEndListAdapter()
    private val args by navArgs<MyFundingFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        initRecyclerView()
        addMyFundingDetailEvent()
    }

    override fun initViewModels() {

    }

    private fun addMyFundingDetailEvent(){
        binding.tvFundingDetail.setOnClickListener {
            navigate(MyFundingFragmentDirections.actionMyFundingFragmentToFundingDetailFragment())
        }
    }

    private fun initRecyclerView(){

    }
}