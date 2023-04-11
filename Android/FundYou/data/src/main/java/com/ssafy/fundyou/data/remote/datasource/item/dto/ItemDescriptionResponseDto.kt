package com.ssafy.fundyou.data.remote.datasource.item.dto

import com.google.gson.annotations.SerializedName

internal data class ItemDescriptionResponseDto(
    @SerializedName("itemType")
    val itemType: String,
    @SerializedName("content")
    val content: String
)