package com.ssafy.fundyou.ui.funding_participate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentInvitedFondueBinding
import com.ssafy.fundyou.ui.common.BaseFragment

class InvitedFondueFragment : BaseFragment<FragmentInvitedFondueBinding>(R.layout.fragment_invited_fondue) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initFundingItem()

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        initUserInfo()
        initFundingItemAdapter()
    }

    override fun initViewModels() {
    }

    private fun initUserInfo(){
    }

    private fun initFundingItem(){
//        with(fundingItemList){
//            add(InvitedFundingModel(0, "유승우", FundingItemModel(
//                id = 0,
//                ProductItemModel(id = 1, price = 3000, "img", "상품이름1", true, "브랜드1", true),
//                tempProductImg = R.drawable.ic_launcher_background,
//                currentFundingPrice = 1000,
//                fundingParticipate = 3,
//            )))
//            add(InvitedFundingModel(0, "유승우", FundingItemModel(
//                id = 0,
//                ProductItemModel(id = 1, price = 3000, "img", "상품이름1", false, "브랜드1", true),
//                tempProductImg = R.drawable.ic_launcher_background,
//                currentFundingPrice = 1000,
//                fundingParticipate = 3,
//            )))
//            add(InvitedFundingModel(0, "유승우", FundingItemModel(
//                id = 0,
//                ProductItemModel(id = 1, price = 3000, "img", "상품이름1", false, "브랜드1", true),
//                tempProductImg = R.drawable.ic_launcher_background,
//                currentFundingPrice = 1000,
//                fundingParticipate = 3,
//            )))
//            add(InvitedFundingModel(0, "유승우", FundingItemModel(
//                id = 0,
//                ProductItemModel(id = 1, price = 3000, "img", "상품이름1", true, "브랜드1", true),
//                tempProductImg = R.drawable.ic_launcher_background,
//                currentFundingPrice = 1000,
//                fundingParticipate = 3,
//            )))
//            add(InvitedFundingModel(0, "유승우", FundingItemModel(
//                id = 0,
//                ProductItemModel(id = 1, price = 3000, "img", "상품이름1", false, "브랜드1", true),
//                tempProductImg = R.drawable.ic_launcher_background,
//                currentFundingPrice = 1000,
//                fundingParticipate = 3,
//            )))
//            add(InvitedFundingModel(0, "유승우", FundingItemModel(
//                id = 0,
//                ProductItemModel(id = 1, price = 3000, "img", "상품이름1", true, "브랜드1", true),
//                tempProductImg = R.drawable.ic_launcher_background,
//                currentFundingPrice = 1000,
//                fundingParticipate = 3,
//            )))
//            add(InvitedFundingModel(0, "유승우", FundingItemModel(
//                id = 0,
//                ProductItemModel(id = 1, price = 3000, "img", "상품이름1", false, "브랜드1", true),
//                tempProductImg = R.drawable.ic_launcher_background,
//                currentFundingPrice = 1000,
//                fundingParticipate = 3,
//            )))
//        }
    }

    private fun initFundingItemAdapter(){

    }
}