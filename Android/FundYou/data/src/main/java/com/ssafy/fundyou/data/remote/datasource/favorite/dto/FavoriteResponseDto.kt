package com.ssafy.fundyou.data.remote.datasource.favorite.dto

import com.google.gson.annotations.SerializedName

data class FavoriteResponseDto(
    @SerializedName("like_id")
    val id: Long,
    @SerializedName("member_id")
    val userId: Long,
    @SerializedName("item_id")
    val itemId: Long
)