package com.ssafy.fundyou.data.remote.mappers

import com.ssafy.fundyou.data.remote.datasource.item.dto.ItemResponseDto
import com.ssafy.fundyou.data.remote.datasource.item.dto.RandomItemResponseDto
import com.ssafy.fundyou.domain.model.item.ProductCategoryModel
import com.ssafy.fundyou.domain.model.item.ProductItemModel

//API 문서 확인 후 수정
internal fun ItemResponseDto.toDomainModel() = ProductItemModel(
    id = this.id,
    price = this.price,
    img = this.img,
    descriptionImg = this.descriptionImg,
    title = this.title,
    isAr = this.isAr,
    description = null,
    isFavorite = this.isFavorite,
    sellingCount = this.sellingCount,
    brand = this.brand,
    category = ProductCategoryModel(
        id = this.category.id,
        categoryName = this.category.categoryName
    )
)

internal fun RandomItemResponseDto.toDomainModel() = ProductItemModel(
    id = this.id,
    price = this.price,
    img = this.img,
    descriptionImg = null,
    title = this.name,
    isAr = this.isAr,
    description = null,
    isFavorite = this.isFavorite,
    sellingCount = null,
    brand = this.brand,
    category = null
)