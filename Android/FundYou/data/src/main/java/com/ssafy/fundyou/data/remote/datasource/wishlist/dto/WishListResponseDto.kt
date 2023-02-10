package com.ssafy.fundyou.data.remote.datasource.wishlist.dto

import com.google.gson.annotations.SerializedName

internal data class WishListResponseDto(
    @SerializedName("count")
    val count: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("isAr")
    val isAr: Boolean,

    @SerializedName("isFavorite")
    val isFavorite: Boolean,

    @SerializedName("itemId")
    val itemId: String,

    @SerializedName("memberId")
    val memberId: String,

    @SerializedName("price")
    val price: Int,

    @SerializedName("title")
    val title: String
)