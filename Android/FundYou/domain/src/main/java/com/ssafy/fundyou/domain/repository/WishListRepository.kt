package com.ssafy.fundyou.domain.repository

import com.ssafy.fundyou.domain.model.wishlist.WishListDomainModel

interface WishListRepository {
    suspend fun getWishListItemList() : List<WishListDomainModel>
    suspend fun addWishListItem(count: Int, itemId: Int): Int
    suspend fun modifyWishListItem(count: Int, itemId: Int): Int
    suspend fun deleteWishListItem(itemId: Int): Int
}