package com.ssafy.fundyou.domain.model.funding

data class FundingStatisticsModel(
    val fundingParticipateMemberId : Long,
    val fundingParticipateMemberProfileImg : String,
    val fundingParticipateMemberName : String,
    val fundingPrice : Int
)