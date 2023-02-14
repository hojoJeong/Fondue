package com.ssafy.fundyou.data.remote.datasource.funding.dto

import com.google.gson.annotations.SerializedName

internal data class FundingIdRequestDto(
    @SerializedName("fundingId")
    val fundingId : Long
)