package com.ssafy.fundyou.ui.funding_my.model

import com.ssafy.fundyou.domain.model.ProductItemModel
import kotlin.math.roundToInt

data class FundingItemModel(
    val id : Int,
    val product : ProductItemModel,
    val tempProductImg : Int,
    val currentFundingPrice : Int,
    val fundingParticipate : Int,
){
    val fundingProgress =  ((currentFundingPrice.toDouble() / product.price.toDouble()) * 100).roundToInt()
}