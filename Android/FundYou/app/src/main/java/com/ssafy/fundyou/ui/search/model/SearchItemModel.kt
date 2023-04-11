package com.ssafy.fundyou.ui.search.model

import com.ssafy.fundyou.domain.model.item.ProductItemModel

data class SearchItemModel(
    val id: Long,
    val itemImg: String,
    val isFavorite: Boolean,
    val isAr: Boolean,
    val brand: String,
    val name: String,
    val price: Int
)

fun ProductItemModel.toUiModel() = SearchItemModel(
    id = this.id,
    itemImg = this.img[0],
    isFavorite = this.isFavorite,
    isAr = this.isAr,
    brand = this.brand,
    name = this.title,
    price = this.price
)
