package com.ssafy.fundyou.ui.funding_my.model

import com.ssafy.fundyou.domain.model.funding.FundingItemInfoModel
import com.ssafy.fundyou.util.extension.getFundingPercentage


data class MyFundingItemInfoUiModel(
    val fundingItemId: Long,
    val img: String,
    val brand: String,
    val title: String,
    val status: Boolean,
    val percentage: Int,
    val participateCount: Int,
    val currentFundingPrice: Int,
    val isAr : Boolean,
    val totalPrice : Int,
    val itemId: Long
)

fun FundingItemInfoModel.toMyFundingItemUiModel() = MyFundingItemInfoUiModel(
    fundingItemId = this.id,
    itemId = this.info.id,
    img = this.info.img[0],
    brand = this.info.brand,
    title = this.info.title,
    status = this.status,
    percentage = getFundingPercentage(this.currentFundingPrice, this.itemTotalPrice),
    participateCount = this.participantsCount,
    currentFundingPrice = this.currentFundingPrice,
    isAr = this.info.isAr,
    totalPrice = this.itemTotalPrice
)