package com.ssafy.fundyou.data.remote.service

import com.ssafy.fundyou.data.remote.datasource.BaseResultDto
import com.ssafy.fundyou.data.remote.datasource.wishlist.dto.WishListRequestDto
import com.ssafy.fundyou.data.remote.datasource.wishlist.dto.WishListResponseDto
import retrofit2.http.*

internal interface WishListApiService {
    @GET("api/cart/list")
    suspend fun getWishListItemList(): List<WishListResponseDto>

    @POST("api/cart")
    suspend fun addWishListItem(@Body request: WishListRequestDto) : BaseResultDto

    @PUT("api/cart")
    suspend fun modifyWishListItem(@Body request: WishListRequestDto) : BaseResultDto

    @DELETE("api/cartItem/{itemId}")
    suspend fun deleteWishListItem(@Path("itemId") itemId: Long) : BaseResultDto

}