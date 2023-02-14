package com.ssafy.fundyou.data.remote.datasource.funding.dto

import com.google.gson.annotations.SerializedName

internal data class FundingParticipateResponseDto(
    @SerializedName("endDate")
    val endDate: Long,

    @SerializedName("fundingPoint")
    val fundingPoint: Int,

    @SerializedName("fundingStatus")
    val fundingStatus: Boolean,

    @SerializedName("id")
    val id: Long,

    @SerializedName("profileImg")
    val fundingHostProfile: String,

    @SerializedName("startDate")
    val startDate: Long,

    @SerializedName("userName")
    val fundingHostName: String
)