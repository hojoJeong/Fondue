package com.ssafy.fundyou.data.remote.datasource.item.dto

import com.google.gson.annotations.SerializedName

internal data class ItemResponseDto(
    @SerializedName("itemId")
    val id: Long,
    @SerializedName("price")
    val price: Int,
    @SerializedName("image")
    val img: String,
    @SerializedName("descriptionImg")
    val descriptionImg: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("isAr")
    val isAr: Boolean,
    @SerializedName("isFavorite")
    val isFavorite: Boolean,
    @SerializedName("sellingCount")
    val sellingCount: Int,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("category")
    val category: ItemCategoryResponseDto,
    @SerializedName("description")
    val description: List<ItemDescriptionResponseDto>?
)