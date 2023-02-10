package com.ssafy.fundyou.data.remote.mappers

import com.ssafy.fundyou.data.remote.datasource.wishlist.dto.WishListResponseDto
import com.ssafy.fundyou.domain.model.wishlist.WishListDomainModel

internal fun WishListResponseDto.toDomainModel() = WishListDomainModel(
    count = this.count,
    image = this.image,
    isAr = this.isAr,
    isFavorite = this.isFavorite,
    itemId = this.itemId,
    memberId = this.memberId,
    price = this.price,
    title = this.title
)