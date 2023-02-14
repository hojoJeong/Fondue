package com.ssafy.fundyou.domain.model.pay

import com.ssafy.fundyou.domain.model.funding.FundingItemInfoModel

data class PayAttendModel(
    val statusMessage: String,
    val fundingItemInfoModel: FundingItemInfoModel?
)