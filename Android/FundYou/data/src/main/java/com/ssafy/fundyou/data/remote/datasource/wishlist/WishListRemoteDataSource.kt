package com.ssafy.fundyou.data.remote.datasource.wishlist

import com.ssafy.fundyou.data.remote.datasource.BaseResultDto
import com.ssafy.fundyou.data.remote.datasource.wishlist.dto.WishListRequestDto
import com.ssafy.fundyou.data.remote.datasource.wishlist.dto.WishListResponseDto

internal interface WishListRemoteDataSource {
    suspend fun getWishListItemList() : List<WishListResponseDto>
    suspend fun addWishListItem(request: WishListRequestDto) : BaseResultDto
    suspend fun modifyWishListItem(request: WishListRequestDto) : BaseResultDto
    suspend fun deleteWishListItem(itemId: Int) : BaseResultDto
}