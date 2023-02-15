package com.ssafy.fundyou.ui.funding_participate.model

import com.ssafy.fundyou.domain.model.funding.FundingItemInfoModel
import com.ssafy.fundyou.ui.funding_my_item_detail.model.toMyFundingItemDetailUiModel
import com.ssafy.fundyou.util.extension.getFundingPercentage
import kotlin.math.roundToInt

data class FundingParticipateItemUiModel(
    val id: Long,
    val arRegistered: Boolean,
    val possibleFundingPrice: Int,
    val status : Boolean,
    val img: String,
    val brand: String,
    val title: String,
    val currentFundingPrice: Int,
    val participateCount: Int,
    val percentage: Int
)

fun FundingItemInfoModel.toFundingParticipateItemUiModel() = FundingParticipateItemUiModel(
    id = this.id,
    arRegistered = this.arImgList.isNotEmpty(),
    possibleFundingPrice = this.itemTotalPrice - this.currentFundingPrice,
    status = this.status,
    img = this.info.img[0],
    brand = this.info.brand,
    title = this.info.title,
    currentFundingPrice = this.currentFundingPrice,
    participateCount = this.participantsCount,
    percentage = getFundingPercentage(this.currentFundingPrice, this.itemTotalPrice)
)

