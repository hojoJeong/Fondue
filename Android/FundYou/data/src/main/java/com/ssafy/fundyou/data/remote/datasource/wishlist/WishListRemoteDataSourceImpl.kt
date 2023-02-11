package com.ssafy.fundyou.data.remote.datasource.wishlist

import com.ssafy.fundyou.data.remote.datasource.BaseResultDto
import com.ssafy.fundyou.data.remote.datasource.wishlist.dto.WishListRequestDto
import com.ssafy.fundyou.data.remote.datasource.wishlist.dto.WishListResponseDto
import com.ssafy.fundyou.data.remote.service.WishListApiService
import javax.inject.Inject

internal class WishListRemoteDataSourceImpl @Inject constructor(private val wishListApiService: WishListApiService) :
    WishListRemoteDataSource {
    override suspend fun getWishListItemList(): List<WishListResponseDto> =
        wishListApiService.getWishListItemList()

    override suspend fun addWishListItem(request: WishListRequestDto): BaseResultDto =
        wishListApiService.addWishListItem(request)

    override suspend fun modifyWishListItem(request: WishListRequestDto): BaseResultDto =
        wishListApiService.modifyWishListItem(request)

    override suspend fun deleteWishListItem(itemId: Long): BaseResultDto =
        wishListApiService.deleteWishListItem(itemId)

}