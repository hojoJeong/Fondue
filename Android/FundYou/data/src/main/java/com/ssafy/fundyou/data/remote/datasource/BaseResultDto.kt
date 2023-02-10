package com.ssafy.fundyou.data.remote.datasource

import com.google.gson.annotations.SerializedName

internal data class BaseResultDto(
    @SerializedName("message")
    val message: String,

    @SerializedName("statusCode")
    val statusCode: Int,

    @SerializedName("data")
    val data: Int
)