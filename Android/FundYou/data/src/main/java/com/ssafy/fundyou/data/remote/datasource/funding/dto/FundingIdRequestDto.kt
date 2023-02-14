package com.ssafy.fundyou.data.remote.datasource.funding.dto

import com.google.gson.annotations.SerializedName

internal data class FundingIdRequestDto(
    @SerializedName("funding_id")
    val fundingId : Long
)

// fundingId, funding_id가 있음...
internal data class FundingIdRequestDto2(
    @SerializedName("fundingId")
    val fundingId : Long
)