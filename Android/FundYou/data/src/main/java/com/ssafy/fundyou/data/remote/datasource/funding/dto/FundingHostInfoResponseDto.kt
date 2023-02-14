package com.ssafy.fundyou.data.remote.datasource.funding.dto

import com.google.gson.annotations.SerializedName

data class FundingHostInfoResponseDto(
    @SerializedName("fundingName")
    val fundingName : String?,
    @SerializedName("hostName")
    val hostName : String?,
    @SerializedName("profileImg")
    val profileImg : String?
)