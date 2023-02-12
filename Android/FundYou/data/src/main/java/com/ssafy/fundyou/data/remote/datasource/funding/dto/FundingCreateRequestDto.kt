package com.ssafy.fundyou.data.remote.datasource.funding.dto

import com.google.gson.annotations.SerializedName

internal data class FundingCreateRequestDto(
    @SerializedName("endDate")
    val endDate : Long?,
    @SerializedName("fundingName")
    val fundingName : String?
)