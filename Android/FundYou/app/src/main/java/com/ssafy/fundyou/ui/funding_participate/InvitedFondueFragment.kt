package com.ssafy.fundyou.ui.funding_participate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentInvitedFondueBinding
import com.ssafy.fundyou.domain.model.ProductItemModel
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.ui.funding_my.model.FundingItemModel
import com.ssafy.fundyou.ui.funding_participate.model.InvitedFundingModel

class InvitedFondueFragment : BaseFragment<FragmentInvitedFondueBinding>(R.layout.fragment_invited_fondue) {
    private val fundingItemList = mutableListOf<InvitedFundingModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    override fun initView() {
        initFundingItem()
    }

    override fun initViewModels() {
    }

    private fun initFundingItem(){
        with(fundingItemList){
            add(InvitedFundingModel(0, "유승우", FundingItemModel(
                id = 0,
                ProductItemModel(id = 1, price = 3000, "img", "상품이름1", true, "브랜드1", true),
                tempProductImg = R.drawable.ic_launcher_background,
                currentFundingPrice = 1000,
                fundingParticipate = 3,
            )))
            add(InvitedFundingModel(0, "유승우", FundingItemModel(
                id = 0,
                ProductItemModel(id = 1, price = 3000, "img", "상품이름1", false, "브랜드1", true),
                tempProductImg = R.drawable.ic_launcher_background,
                currentFundingPrice = 1000,
                fundingParticipate = 3,
            )))
            add(InvitedFundingModel(0, "유승우", FundingItemModel(
                id = 0,
                ProductItemModel(id = 1, price = 3000, "img", "상품이름1", false, "브랜드1", true),
                tempProductImg = R.drawable.ic_launcher_background,
                currentFundingPrice = 1000,
                fundingParticipate = 3,
            )))
            add(InvitedFundingModel(0, "유승우", FundingItemModel(
                id = 0,
                ProductItemModel(id = 1, price = 3000, "img", "상품이름1", true, "브랜드1", true),
                tempProductImg = R.drawable.ic_launcher_background,
                currentFundingPrice = 1000,
                fundingParticipate = 3,
            )))
            add(InvitedFundingModel(0, "유승우", FundingItemModel(
                id = 0,
                ProductItemModel(id = 1, price = 3000, "img", "상품이름1", false, "브랜드1", true),
                tempProductImg = R.drawable.ic_launcher_background,
                currentFundingPrice = 1000,
                fundingParticipate = 3,
            )))
            add(InvitedFundingModel(0, "유승우", FundingItemModel(
                id = 0,
                ProductItemModel(id = 1, price = 3000, "img", "상품이름1", true, "브랜드1", true),
                tempProductImg = R.drawable.ic_launcher_background,
                currentFundingPrice = 1000,
                fundingParticipate = 3,
            )))
            add(InvitedFundingModel(0, "유승우", FundingItemModel(
                id = 0,
                ProductItemModel(id = 1, price = 3000, "img", "상품이름1", false, "브랜드1", true),
                tempProductImg = R.drawable.ic_launcher_background,
                currentFundingPrice = 1000,
                fundingParticipate = 3,
            )))
        }
        initFundingItemAdapter()
    }

    private fun initFundingItemAdapter(){
        val fundingItemAdapter = InvitedFondueItemAdapter()
        fundingItemAdapter.submitList(fundingItemList)
        with(binding.rvInvitedFondue){
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = fundingItemAdapter
        }
    }
}