package com.ssafy.fundyou.ui.funding_my_detail.model

import com.ssafy.fundyou.domain.model.funding.FundingItemInfoModel

data class FundingItemStateUiModel(
    val id : Long,
    val brand : String,
    val img : String,
    val title : String,
    val currentFundingPrice : Int,
    val itemTotalPrice : Int,
)

fun FundingItemInfoModel.toFundingItemStateUiModel() = FundingItemStateUiModel(
    id = this.id,
    brand = this.info.brand,
    img = this.info.img[0],
    title = this.info.title,
    currentFundingPrice = this.currentFundingPrice,
    itemTotalPrice = this.info.price * this.itemCount
)