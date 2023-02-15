package com.ssafy.fundyou.data.remote.datasource.pay.dto

import com.google.gson.annotations.SerializedName
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingItemResponseDto

internal data class PayAttendResponseDto(
    @SerializedName("data")
    val fundingItemResponse : FundingItemResponseDto?,
    @SerializedName("statusCode")
    val statusCode : Int?,
    @SerializedName("message")
    val message : String?
)