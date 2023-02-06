package com.ssafy.fundyou.ui.funding_detail

import android.os.Bundle
import android.view.View
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentFundingDetailBinding
import com.ssafy.fundyou.domain.model.ProductItemModel
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.ui.funding_detail.adapter.FundingDetailStateAdapter
import com.ssafy.fundyou.ui.funding_detail.adapter.FundingRankingAdapter
import com.ssafy.fundyou.ui.funding_detail.model.FundingUserModel
import com.ssafy.fundyou.ui.funding_my.model.FundingItemModel
import com.ssafy.fundyou.util.getColorNoTheme

class FundingDetailFragment :
    BaseFragment<FragmentFundingDetailBinding>(R.layout.fragment_funding_detail) {

    private val fundingRankingAdapter = FundingRankingAdapter()
    private val fundingDetailStateAdapter = FundingDetailStateAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        initFundingPieChart()
        initFundingRankingList()
        initFundingStateList()
    }

    override fun initViewModels() {

    }

    private fun initFundingRankingList() {
        val tempList = listOf(
            FundingUserModel(
                1,
                "유승우",
                R.drawable.ic_launcher_background,
                100000,
                listOf("냉장고", "전자레인지")
            ),
            FundingUserModel(2, "정수용", R.drawable.ic_launcher_background, 10000, listOf("냉장고")),
            FundingUserModel(
                3,
                "정호조",
                R.drawable.ic_launcher_background,
                300000,
                listOf("냉장고", "전자레인지", "가스레인지")
            )
        )
        fundingRankingAdapter.submitList(tempList)
        binding.rvFundingRanking.adapter = fundingRankingAdapter
    }

    private fun initFundingStateList() {
        val tempList = listOf(
            FundingItemModel(
                id = 0,
                ProductItemModel(id = 1, price = 3000, "img", "상품이름1", false, "브랜드1", true),
                tempProductImg = R.drawable.ic_launcher_background,
                currentFundingPrice = 1000,
                fundingParticipate = 3,
            ), FundingItemModel(
                id = 1,
                ProductItemModel(id = 2, price = 30000, "img", "상품이름2", false, "브랜드3", true),
                tempProductImg = R.drawable.ic_launcher_background,
                currentFundingPrice = 30000,
                fundingParticipate = 5,
            ),
            FundingItemModel(
                id = 2,
                ProductItemModel(id = 2, price = 30000, "img", "상품이름2", false, "브랜드3", true),
                tempProductImg = R.drawable.ic_launcher_background,
                currentFundingPrice = 30000,
                fundingParticipate = 5,
            )
        )
        fundingDetailStateAdapter.submitList(tempList)
        fundingDetailStateAdapter.addParticipateButtonEvent { id ->
            navigate(FundingDetailFragmentDirections.actionFundingDetailFragmentToFundingParticipationFragment(id))
        }
        binding.rvFundingItemDetail.adapter = fundingDetailStateAdapter
    }

    private fun initFundingPieChart() {
        // 파이차트에 들어갈 자료들
        val pieEntries = arrayListOf<PieEntry>()

        // 파이차트의 자료(Key, Value)
        val tempMap = mapOf(
            ("test1" to 10), ("test3" to 30), ("test2" to 20), ("last" to 40)
        )

        // 파이차트에 표현할 색상, Map의 순서와 동일하게 적용
        val colorList = arrayListOf(
            requireContext().getColorNoTheme(R.color.franch_rose),
            requireContext().getColorNoTheme(R.color.golden_yellow),
            requireContext().getColorNoTheme(R.color.pig_pink),
            requireContext().getColorNoTheme(R.color.grey)
        )

        // 데이터 추가
        for (value in tempMap.keys) {
            pieEntries.add(PieEntry(tempMap[value]?.toFloat()!!, value))
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
            centerText = "총 금액 10000"
            setCenterTextColor(requireContext().getColorNoTheme(R.color.black))
            setCenterTextSize(20f)

            // 차트 하단 요약 해제
            legend.isEnabled = false
        }

        binding.chartFunding.invalidate()
    }
}