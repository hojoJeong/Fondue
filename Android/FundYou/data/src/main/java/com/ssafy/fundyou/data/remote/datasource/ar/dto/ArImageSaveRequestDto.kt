package com.ssafy.fundyou.data.remote.datasource.ar.dto

import com.google.gson.annotations.SerializedName

internal data class ArImageSaveRequestDto(
    @SerializedName("funding_id")
    val fundingId: Long,
    @SerializedName("item_id")
    val itemId: Long,
    @SerializedName("url")
    val url: String
)