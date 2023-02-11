package com.ssafy.fundyou.domain.model.funding

data class FundingInfoModel(
    val id: Long,
    val fundingName : String,
    val fundingStatus: Boolean,
    val startDate : Long,
    val endDate: Long,
    val currentFundingPrice: Int,
    val totalPrice: Int,
    val percentage: Float,
)
