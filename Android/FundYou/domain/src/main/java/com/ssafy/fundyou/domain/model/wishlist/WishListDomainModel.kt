package com.ssafy.fundyou.domain.model.wishlist

data class WishListDomainModel(
    val count: String,
    val image: String,
    val isAr: Boolean,
    val isFavorite: Boolean,
    val itemId: String,
    val memberId: String,
    val price: Int,
    val title: String
)