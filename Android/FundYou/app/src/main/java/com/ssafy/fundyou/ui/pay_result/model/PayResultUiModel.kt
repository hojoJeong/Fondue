package com.ssafy.fundyou.ui.pay_result.model

import android.os.Parcelable
import com.ssafy.fundyou.domain.model.pay.PayAttendModel
import com.ssafy.fundyou.ui.funding_my_item_detail.model.toMyFundingItemDetailUiModel
import com.ssafy.fundyou.util.extension.getFundingPercentage
import kotlinx.parcelize.Parcelize
import kotlin.math.roundToInt

@Parcelize
data class PayResultUiModel(
    val fundingItemId: Long,
    val message: String,
    val payState : Boolean,
    val fundingHostUserName : String,
    val img: String,
    val brand: String,
    val title: String,
    val currentFundingPrice: Int,
    val percentage: Int,
    val itemTotalPrice: Int
) : Parcelable

fun PayAttendModel.toPayResultUiModel() = PayResultUiModel(
    fundingItemId = this.fundingItemInfoModel?.id ?: -1,
    message = this.statusMessage,
    fundingHostUserName = this.fundingItemInfoModel?.fundingHostUserName ?: "",
    payState = this.statusMessage == "펀딩 성공",
    img = this.fundingItemInfoModel?.info?.img?.get(0) ?: "",
    brand = this.fundingItemInfoModel?.info?.brand ?: "",
    title = this.fundingItemInfoModel?.info?.title ?: "",
    currentFundingPrice = this.fundingItemInfoModel?.currentFundingPrice ?: -1,
    percentage = getFundingPercentage(this.fundingItemInfoModel?.currentFundingPrice ?: 0, this.fundingItemInfoModel?.itemTotalPrice ?: 0),
    itemTotalPrice = this.fundingItemInfoModel?.itemTotalPrice ?: 0
)