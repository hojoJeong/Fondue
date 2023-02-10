package com.ssafy.fundyou.ui.item_detail.model

import com.ssafy.fundyou.domain.model.item.ProductItemModel

data class RelatedItemModel(
    val id: Long,
    val brand: String,
    val img: String,
    val title: String,
    val price: Int,
    val isAr : Boolean
)

fun ProductItemModel.toRelatedItemModel() = RelatedItemModel(
    id = this.id,
    brand = this.brand,
    img = this.img[0],
    title = this.title,
    price = this.price,
    isAr = this.isAr
)
