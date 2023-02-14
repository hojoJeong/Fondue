package com.ssafy.fundyou.data.remote.datasource.ar.dto

import com.google.gson.annotations.SerializedName

internal data class ArImageResponseDto(
    @SerializedName("funding_item_id")
    val fundingItemId: Long,
    @SerializedName("id")
    val id: Long,
    @SerializedName("url")
    val url: String
)

