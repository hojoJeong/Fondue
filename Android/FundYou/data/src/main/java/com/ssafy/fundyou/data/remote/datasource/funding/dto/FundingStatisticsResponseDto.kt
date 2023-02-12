package com.ssafy.fundyou.data.remote.datasource.funding.dto

import com.google.gson.annotations.SerializedName

data class FundingStatisticsResponseDto(
    @SerializedName("attendedPrice")
    val attendedPrice : Int?,
    @SerializedName("memberId")
    val memberId : Long?,
    @SerializedName("profileImg")
    val profileImg : String?,
    @SerializedName("username")
    val userName : String?
)
