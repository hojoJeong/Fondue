package com.ssafy.fundyou.domain.model.funding

data class FundingParticipateModel(
    val id: Long,
    val fundingHostName: String,
    val fundingStatus: Boolean,
    val startDate: Long,
    val endDate: Long,
    val fundingPoint: Int,
    val fundingHostProfile: String
)