package com.ssafy.fundyou.ui.favorite.model

data class FavoriteModel(
    val id: Long,
    val title: String,
    val brand: String,
    val price: Int,
    val img: String,
    val isAr: Boolean
)