package com.ssafy.fundyou.ui.funding_my_list.model

import com.ssafy.fundyou.domain.model.funding.FundingTotalModel

data class MyFundingListUiModel(
    val id: Long,
    val fundingName: String,
    val startDate: Long,
    val endDate: Long,
    val status: Boolean,
    val currentFundingPrice: Int,
    val totalFundingPrice: Int,
    val imgList: List<String>
)

fun FundingTotalModel.toFundingListUiModel() = MyFundingListUiModel(
    id = this.fundingInfo.id,
    fundingName = this.fundingInfo.fundingName,
    startDate = this.fundingInfo.startDate,
    endDate = this.fundingInfo.endDate,
    status = this.fundingInfo.fundingStatus,
    currentFundingPrice = this.fundingInfo.currentFundingPrice,
    totalFundingPrice = this.fundingInfo.totalPrice,
    imgList = this.fundingItem.filter { fundingItemModel -> fundingItemModel.id == fundingItemModel.info.id }[0].info.img
)