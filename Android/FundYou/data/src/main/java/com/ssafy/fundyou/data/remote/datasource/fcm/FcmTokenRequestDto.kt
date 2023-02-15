package com.ssafy.fundyou.data.remote.datasource.fcm

import com.google.gson.annotations.SerializedName

internal data class FcmTokenRequestDto(
    @SerializedName("targetToken")
    val token: String
)