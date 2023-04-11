package com.ssafy.fundyou.domain.model.wishlist

data class WishListDomainModel(
    val count: Int,
    val image: String,
    val isAr: Boolean,
    val isFavorite: Boolean,
    val itemId: Long,
    val memberId: Long,
    val price: Int,
    val title: String,
    val brand: String
)