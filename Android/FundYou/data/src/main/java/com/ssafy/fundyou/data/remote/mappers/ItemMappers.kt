package com.ssafy.fundyou.data.remote.mappers.item

import com.ssafy.fundyou.data.remote.datasource.item.dto.ItemResponseDto
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
    isFavorite = this.isFavorite,
    description = this.description,
    sellingCount = this.sellingCount,
    brand = this.brand,
    category = ProductCategoryModel(
        id = this.category.id,
        categoryName = this.category.categoryName
    )
)