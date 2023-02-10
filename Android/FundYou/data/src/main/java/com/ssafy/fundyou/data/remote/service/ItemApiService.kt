package com.ssafy.fundyou.data.remote.service

import com.ssafy.fundyou.data.remote.datasource.item.dto.ItemResponseDto
import com.ssafy.fundyou.data.remote.datasource.item.dto.ItemSearchRequestDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

internal interface ItemApiService {
    @GET("/item/list")
    suspend fun getAllProductItemList(): List<ItemResponseDto>

    @GET("/item/ranking/{categoryId}/{minPrice}/{maxPrice}")
    suspend fun getRankingItemList(
        @Path("categoryId") categoryId: Int,
        @Path("minPrice") minPrice: Int,
        @Path("maxPrice") maxPrice: Int
    ): List<ItemResponseDto>

    @GET("/item/random")
    suspend fun getRandomItemList(): List<ItemResponseDto>

    @GET("/favorite/like/list")
    suspend fun getFavoriteItemList(): List<ItemResponseDto>

    @GET("/item/category/{categoryId}")
    suspend fun getCategoryItemList(@Path("categoryId") categoryId: Int): List<ItemResponseDto>

    @GET("/item/category/{categoryId}/{minPrice}/{maxPrice}")
    suspend fun getItemByPrice(
        @Path("categoryId") categoryId: Int,
        @Path("minPrice") minPrice: Int,
        @Path("maxPrice") maxPrice: Int
    ): List<ItemResponseDto>

    @POST("/search")
    suspend fun getKeywordItemList(@Body request: ItemSearchRequestDto): List<ItemResponseDto>

    @GET("/item/{id}")
    suspend fun getItemDetailInfo(@Path("id") itemId: Long): ItemResponseDto
}