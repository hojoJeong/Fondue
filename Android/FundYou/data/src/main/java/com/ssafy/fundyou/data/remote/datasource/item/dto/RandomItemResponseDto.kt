package com.ssafy.fundyou.data.remote.datasource.item.dto

import com.google.gson.annotations.SerializedName

internal data class RandomItemResponseDto(
    @SerializedName("itemId")
    val id: Long,

    @SerializedName("image")
    val img: String,

    @SerializedName("isAr")
    val isAr: Boolean,

    @SerializedName("isFavorite")
    val isFavorite: Boolean,

    @SerializedName("price")
    val price: Int,

    @SerializedName("title")
    val name: String,

    @SerializedName("brand")
    val brand: String
)