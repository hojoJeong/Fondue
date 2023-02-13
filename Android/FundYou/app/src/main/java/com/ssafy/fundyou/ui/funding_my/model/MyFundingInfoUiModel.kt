package com.ssafy.fundyou.ui.funding_my.model

import com.ssafy.fundyou.domain.model.funding.FundingInfoModel
import com.ssafy.fundyou.util.extension.getDeadLineByEndDate

data class MyFundingInfoUiModel(
    val id : Long,
    val deadLine : Int,
    val percentage : Int,
    val status : Boolean
)

fun FundingInfoModel.toMyFundingInfoUiModel() = MyFundingInfoUiModel(
    id = this.id,
    deadLine = getDeadLineByEndDate(this.endDate),
    percentage = this.percentage,
    status = this.fundingStatus
)