package com.ssafy.fundyou.data.remote.datasource.funding.dto

import com.google.gson.annotations.SerializedName

// fundingId, funding_id가 있음...
internal data class FundingIdRequestDto(
    @SerializedName("fundingId")
    val fundingId : Long
)