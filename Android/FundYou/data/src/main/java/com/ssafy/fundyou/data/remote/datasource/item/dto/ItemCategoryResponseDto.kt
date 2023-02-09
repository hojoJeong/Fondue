package com.ssafy.fundyou.data.remote.datasource.item.dto

import com.google.gson.annotations.SerializedName

internal data class ItemCategoryResponseDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("categoryName")
    val categoryName: String
)