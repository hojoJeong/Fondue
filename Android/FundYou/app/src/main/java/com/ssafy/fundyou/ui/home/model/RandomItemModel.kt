package com.ssafy.fundyou.ui.home.model

import com.ssafy.fundyou.domain.model.item.ProductItemModel

data class RandomItemModel(
    val id: Long,
    val title: String,
    val brand: String,
    val price: Int,
    val img: String,
    val isAr: Boolean
)

fun ProductItemModel.toRandomItemModel() = RandomItemModel(
    id = this.id,
    title = this.title,
    brand = this.brand,
    price = this.price,
    img = this.img[0],
    isAr = this.isAr
)