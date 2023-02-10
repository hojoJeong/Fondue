package com.ssafy.fundyou.data.remote.datasource.wishlist.dto

import com.google.gson.annotations.SerializedName

internal data class WishListResponseDto(
    @SerializedName("count")
    val count: Int,

    @SerializedName("image")
    val image: String,

    @SerializedName("isAr")
    val isAr: Boolean,

    @SerializedName("isFavorite")
    val isFavorite: Boolean,

    @SerializedName("itemId")
    val itemId: Long,

    @SerializedName("memberId")
    val memberId: Long,

    @SerializedName("price")
    val price: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("brand")
    val brand: String
)