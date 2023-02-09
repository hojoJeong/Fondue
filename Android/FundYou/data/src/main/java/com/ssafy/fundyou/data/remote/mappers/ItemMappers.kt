package com.ssafy.fundyou.data.remote.mappers

import com.ssafy.fundyou.data.remote.datasource.item.dto.ItemDescriptionResponseDto
import com.ssafy.fundyou.data.remote.datasource.item.dto.ItemResponseDto
import com.ssafy.fundyou.domain.model.item.ProductCategoryModel
import com.ssafy.fundyou.domain.model.item.ProductDescriptionModel
import com.ssafy.fundyou.domain.model.item.ProductItemModel

internal fun ItemResponseDto.toDomainModel() = ProductItemModel(
    id = this.id,
    price = this.price,
    img = this.img.split(" "),
    descriptionImg = this.descriptionImg,
    title = this.title,
    isAr = this.isAr,
    description = this.description.map { it.toDomainModel() },
    isFavorite = this.isFavorite,
    sellingCount = this.sellingCount,
    brand = this.brand,
    category = ProductCategoryModel(
        id = this.category.id,
        categoryName = this.category.categoryName
    )
)

internal fun ItemDescriptionResponseDto.toDomainModel() = ProductDescriptionModel(
    type = this.type,
    value = this.value
)