package com.ssafy.fundyou.ui.funding_my_detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentMyFundingDetailBinding
import com.ssafy.fundyou.ui.common.BaseFragment
import com.ssafy.fundyou.ui.funding_my_detail.adapter.FundingItemStateAdapter
import com.ssafy.fundyou.ui.funding_my_detail.adapter.FundingParticipateUserAdapter
import com.ssafy.fundyou.ui.funding_my_detail.model.FundingParticipateUserUiModel
import com.ssafy.fundyou.util.extension.getColorNoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFundingDetailFragment :
    BaseFragment<FragmentMyFundingDetailBinding>(R.layout.fragment_my_funding_detail) {

    private val args by navArgs<MyFundingDetailFragmentArgs>()
    private val myFundingDetailViewModel by viewModels<MyFundingDetailViewModel>()
    private val participateUserAdapter = FundingParticipateUserAdapter()
    private val itemStateAdapter = FundingItemStateAdapter().apply {
        addButtonClickListener { fundingItemId ->
            navigate(MyFundingDetailFragmentDirections.actionMyFundingDetailFragmentToMyFundingItemDetailFragment(fundingItemId))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        myFundingDetailViewModel.getFundingStatistics(args.fundingId)
        myFundingDetailViewModel.getFundingItemList(args.fundingId)
    }

    override fun initViewModels() {
        initFundingStatisticsObserver()
        initFundingItemObserver()
    }

    private fun initFundingStatisticsObserver() {
        myFundingDetailViewModel.fundingStatisticsList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initFundingStatisticsObserver: loading...")
                }
                is ViewState.Success -> {
                    val result = response.value ?: emptyList()
                    if (result.isEmpty()) {
                        setNoFundingParticipateLayout()
                    } else {
                        setFundingParticipateLayout(result)
                        initFundingPieChart(result)
                    }
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initFundingStatisticsObserver: error.. ${response.message}")
                }
            }
        }
    }

    private fun initFundingItemObserver() {
        myFundingDetailViewModel.fundingItemList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initFundingItemObserver: loading..")
                }
                is ViewState.Success -> {
                    itemStateAdapter.submitList(response.value ?: emptyList())
                    binding.rvFundingItemDetail.adapter = itemStateAdapter
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initFundingItemObserver: error... ${response.message}")
                }
            }
        }
    }

    private fun setNoFundingParticipateLayout() {
        with(binding) {
            chartFunding.visibility = View.INVISIBLE
            divPieChartBase.visibility = View.GONE
            divPieChartLine.visibility = View.GONE
            tvFundingRankingTitle.visibility = View.GONE
            rvFundingRanking.visibility = View.GONE

            with(lyNoFunding) {
                root.visibility = View.VISIBLE
                tvNoKeyword.text = "현재 펀딩한 사용자가 없습니다."
            }
        }
    }

    private fun setFundingParticipateLayout(fundingStateList: List<FundingParticipateUserUiModel>) {
        with(binding) {
            divPieChartBase.visibility = View.VISIBLE
            divPieChartLine.visibility = View.VISIBLE
            tvFundingRankingTitle.visibility = View.VISIBLE
            rvFundingRanking.visibility = View.VISIBLE
        }
        binding.rvFundingRanking.adapter = participateUserAdapter
        participateUserAdapter.submitList(fundingStateList)
    }

    private fun initFundingPieChart(participateUserList: List<FundingParticipateUserUiModel>) {
        // 파이차트에 들어갈 자료들
        val pieEntries = arrayListOf<PieEntry>()

        var totalPrice = 0

        // 파이차트의 자료(Key, Value)
        val participateMap = mutableMapOf<String, Int>()
        participateUserList.forEach { model ->
            participateMap[model.participateUserName] = model.fundingPrice
            totalPrice += model.fundingPrice
        }

        // 파이차트에 표현할 색상, Map의 순서와 동일하게 적용
        val colorList = arrayListOf(
            requireContext().getColorNoTheme(R.color.franch_rose),
            requireContext().getColorNoTheme(R.color.golden_yellow),
            requireContext().getColorNoTheme(R.color.pig_pink),
            requireContext().getColorNoTheme(R.color.grey)
        )

        // 데이터 추가
        for (value in participateMap.keys) {
            pieEntries.add(PieEntry(participateMap[value]?.toFloat()!!, value))
        }

        // 데이터 set 지정
        val pieDataSet = PieDataSet(pieEntries, "").apply {
            valueTextSize = 12f
            colors = colorList
        }

        // 데이터 형식으로 변경
        val pieData = PieData(pieDataSet).apply {
            setDrawValues(false)
        }

        // 파이차트에 적용
        with(binding.chartFunding) {
            data = pieData

            // label 지정
            setDrawEntryLabels(false)

            // 차트마다 보이는 요약 해제
            description.isEnabled = false

            // 차트 가운데 텍스트
            centerText = "총 금액 ${totalPrice}원"
            setCenterTextColor(requireContext().getColorNoTheme(R.color.black))
            setCenterTextSize(20f)

            // 차트 하단 요약 해제
            legend.isEnabled = false
        }

        binding.chartFunding.invalidate()
    }

    companion object {
        private const val TAG = "MyFundingDetailFragment..."
    }
}