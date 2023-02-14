package com.ssafy.fundyou.ui.funding_my.model

import com.ssafy.fundyou.domain.model.funding.FundingItemInfoModel
import kotlin.math.roundToInt


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
    val totalPrice : Int
)

fun FundingItemInfoModel.toMyFundingItemUiModel() = MyFundingItemInfoUiModel(
    fundingItemId = this.id,
    img = this.info.img[0],
    brand = this.info.brand,
    title = this.info.title,
    status = this.status,
    percentage = ((this.currentFundingPrice.toDouble() / this.itemPrice.toDouble()) * 100).roundToInt(),
    participateCount = this.participantsCount,
    currentFundingPrice = this.currentFundingPrice,
    isAr = this.info.isAr,
    totalPrice = this.itemPrice
)