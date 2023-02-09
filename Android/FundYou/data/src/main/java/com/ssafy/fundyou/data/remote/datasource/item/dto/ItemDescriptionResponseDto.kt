package com.ssafy.fundyou.data.remote.datasource.item.dto

import com.google.gson.annotations.SerializedName

internal data class ItemDescriptionResponseDto(
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val value: String
)