package com.ssafy.fundyou.data.remote.datasource.wishlist.dto

import com.google.gson.annotations.SerializedName

internal data class WishListRequestDto(
    @SerializedName("count")
    val count: Int,

    @SerializedName("itemId")
    val itemId: Int
)