package com.ssafy.fundyou.data.remote.datasource.ar.dto

import com.google.gson.annotations.SerializedName

internal data class ArImageResponseDto(
    @SerializedName("funding_id")
    val fundingId: Long,
    @SerializedName("id")
    val id: Long,
    @SerializedName("item_id")
    val itemId: Long,
    @SerializedName("member_id")
    val memberId: Long,
    @SerializedName("url")
    val url: String
)

