package com.ssafy.fundyou.data.remote.datasource.item.dto

import com.google.gson.annotations.SerializedName

internal data class ItemSearchRequestDto(
    @SerializedName("keyword")
    val keyword: String,
    @SerializedName("max_price")
    val maxPrice: Int,
    @SerializedName("min_price")
    val minPrice: Int
)