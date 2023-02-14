package com.ssafy.fundyou.ui.pay.model

import com.ssafy.fundyou.domain.model.funding.FundingItemInfoModel
import kotlin.math.roundToInt

data class FundingPayItemUiModel(
    val fundingItemId : Long,
    val img : String,
    val brand : String,
    val title : String,
    val itemPrice : Int,
    val fundingBalancePrice : Int,
    val percentage : Int,
)

fun FundingItemInfoModel.toFundingPayItemUiModel() = FundingPayItemUiModel(
    fundingItemId = this.id,
    img = this.info.img[0],
    brand = this.info.brand,
    title = this.info.title,
    itemPrice = this.itemPrice,
    fundingBalancePrice = this.itemPrice - this.currentFundingPrice,
    percentage = (((this.currentFundingPrice).toDouble() / (this.itemPrice).toDouble()) * 100).roundToInt()
)