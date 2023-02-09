package com.ssafy.fundyou.domain.model.item

data class ItemDetailModel(
    val id: Int,
    val price: Int,
    val imgList: List<String>,
    val title: String,
    val isAr: Boolean,
    val description: List<ItemDescriptionModel>,
    val brand: String,
    val isFavorite: Boolean,
    val sellingCount: Int,
    val categoryModel: ItemCategoryModel
)