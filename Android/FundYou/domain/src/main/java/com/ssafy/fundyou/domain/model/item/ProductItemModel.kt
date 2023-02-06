package com.ssafy.fundyou.domain.model.item

data class ProductItemModel(
    val id: Long,
    val price: Int,
    val img: String,
    val descriptionImg: String,
    val title: String,
    val isAr: Boolean,
    val description: MutableList<ProductDescriptionModel>
)