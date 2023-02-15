package com.ssafy.fundyou.ui.pay.model

import com.ssafy.fundyou.domain.model.funding.FundingItemInfoModel
import com.ssafy.fundyou.ui.funding_my_item_detail.model.toMyFundingItemDetailUiModel
import com.ssafy.fundyou.util.extension.getFundingPercentage
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
    itemPrice = this.itemTotalPrice * this.itemCount,
    fundingBalancePrice = (this.itemTotalPrice * this.itemCount) - this.currentFundingPrice,
    percentage = getFundingPercentage(this.currentFundingPrice, this.itemTotalPrice)
)