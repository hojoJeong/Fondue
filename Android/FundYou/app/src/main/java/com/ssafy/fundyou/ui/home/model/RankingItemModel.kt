package com.ssafy.fundyou.ui.home.model

import com.ssafy.fundyou.domain.model.item.ProductItemModel

data class RankingItemModel(
    val id: Long,
    val title: String,
    val brand: String,
    val price: Int,
    val img: String,
    val isAr: Boolean,
    val isFavorite: Boolean
)

fun ProductItemModel.toRankingModel() = RankingItemModel(
    id = this.id,
    title = this.title,
    brand = this.brand,
    price = this.price,
    img = this.img,
    isAr = this.isAr,
    isFavorite = this.isFavorite
)