package com.ssafy.fundyou.ui.funding_my_item_detail.model

import com.ssafy.fundyou.domain.model.funding.FundingItemInfoModel
import com.ssafy.fundyou.util.extension.getFundingPercentage
import kotlin.math.roundToInt

data class MyFundingItemDetailUiModel(
    val id: Long,
    val brand: String,
    val title: String,
    val img: String,
    val participateCount: Int,
    val itemOrderCount: Int,
    val currentFundingPrice: Int,
    val status: Boolean,
    val itemTotalPrice: Int,
    val percentage : Int
)

fun FundingItemInfoModel.toMyFundingItemDetailUiModel() = MyFundingItemDetailUiModel(
    id = this.id,
    brand = this.info.brand,
    title = this.info.title,
    img = this.info.img[0],
    participateCount = this.participantsCount,
    itemOrderCount = this.itemCount,
    currentFundingPrice = this.currentFundingPrice,
    status = this.status,
    itemTotalPrice = this.itemTotalPrice * this.itemCount,
    percentage =  getFundingPercentage(this.currentFundingPrice, this.itemTotalPrice)
)


