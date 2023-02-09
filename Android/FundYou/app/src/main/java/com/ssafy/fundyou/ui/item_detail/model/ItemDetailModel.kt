package com.ssafy.fundyou.ui.item_detail.model

import com.ssafy.fundyou.domain.model.item.ProductItemModel

data class ItemDetailModel(
    val id: Long,
    val price: Int,
    val imgList: List<String>,
    val descriptionImg: String,
    val title: String,
    val isAr: Boolean,
    val isFavorite: Boolean,
    val description: List<ItemDetailDescriptionModel>,
    val brand: String,
    val category: String
)

fun ProductItemModel.toItemDetailModel() = ItemDetailModel(
    id = this.id,
    price = this.price,
    imgList = this.img,
    descriptionImg = this.descriptionImg,
    title = this.title,
    isAr = this.isAr,
    isFavorite = this.isFavorite,
    description = this.description.map { it.toItemDetailModel() },
    brand = this.brand,
    category = this.category.categoryName
)