package com.ssafy.fundyou.ui.wishlist

import com.ssafy.fundyou.domain.model.wishlist.WishListDomainModel

data class WishListModel(
    val count: String,
    val image: String,
    val isAr: Boolean,
    val isFavorite: Boolean,
    val itemId: String,
    val memberId: String,
    val price: Int,
    val title: String
)

fun WishListDomainModel.toUiModel() = WishListModel(
    count = this.count,
    image = this.image,
    isAr = this.isAr,
    isFavorite = this.isFavorite,
    itemId = this.itemId,
    memberId = this.memberId,
    price = this.price,
    title = this.title
)