package com.ssafy.fundyou.domain.model.funding

data class FundingInfoModel(
    val id: Long,
    val fundingStatus: Boolean,
    val endDate: Long,
    val currentFundingPrice: Int,
    val totalPrice: Int,
    val percentage: Float,
)
