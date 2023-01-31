package com.ssafy.fundyou.domain.model

data class ProductItemModel(
    val id: Long,
    val price: Int,
    val img: String,
    val name: String,
    val isAr: Boolean,
    val brand: String,
    val isFavorite: Boolean
)