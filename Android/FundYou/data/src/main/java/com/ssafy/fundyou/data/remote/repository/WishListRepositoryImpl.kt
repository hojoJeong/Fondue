package com.ssafy.fundyou.data.remote.repository

import com.ssafy.fundyou.data.remote.datasource.wishlist.WishListRemoteDataSource
import com.ssafy.fundyou.data.remote.datasource.wishlist.dto.WishListRequestDto
import com.ssafy.fundyou.data.remote.mappers.toDomainModel
import com.ssafy.fundyou.domain.model.wishlist.WishListDomainModel
import com.ssafy.fundyou.domain.repository.WishListRepository
import javax.inject.Inject

internal class WishListRepositoryImpl @Inject constructor(private val wishListRemoteDataSource: WishListRemoteDataSource) : WishListRepository {
    override suspend fun getWishListItemList(): List<WishListDomainModel> = wishListRemoteDataSource.getWishListItemList().map { it.toDomainModel() }


    override suspend fun addWishListItem(count: Int, itemId: Int): Int {
        val request = WishListRequestDto(count, itemId)
        return wishListRemoteDataSource.addWishListItem(request).statusCode
    }

    override suspend fun modifyWishListItem(count: Int, itemId: Int) : Int {
        val request = WishListRequestDto(count, itemId)
        return wishListRemoteDataSource.addWishListItem(request).statusCode
    }

    override suspend fun deleteWishListItem(itemId: Int) : Int {
        return wishListRemoteDataSource.deleteWishListItem(itemId).statusCode
    }
}