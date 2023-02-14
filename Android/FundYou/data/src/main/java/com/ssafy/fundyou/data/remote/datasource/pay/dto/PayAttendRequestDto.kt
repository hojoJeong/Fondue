package com.ssafy.fundyou.data.remote.datasource.pay.dto

import com.google.gson.annotations.SerializedName

data class PayAttendRequestDto(
    @SerializedName("fundingItemId")
    val fundingItemId : Long,
    @SerializedName("message")
    val fundingParticipateMessage : String,
    @SerializedName("point")
    val point : Int
)