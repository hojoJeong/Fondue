package com.ssafy.fundyou.domain.model

data class ProductItemlModel(
    val id: Long,
    val price: String,
    val img: String,
    val name: String,
    val isAr: Boolean,
    val brand: String,
    val isFavorite: Boolean
)