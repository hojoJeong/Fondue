package com.ssafy.fundyou.ui.item_list.model

import com.ssafy.fundyou.domain.model.item.ProductItemModel

data class ItemListModel (
    val id: Long,
    val title: String,
    val brand: String,
    val price: Int,
    val img: String,
    val isAr: Boolean,
    var isFavorite: Boolean
)

fun ProductItemModel.toItemListModel() = ItemListModel(
    id = this.id,
    title = this.title,
    brand = this.brand,
    price = this.price,
    img = this.img[0],
    isAr = this.isAr,
    isFavorite = this.isFavorite
)