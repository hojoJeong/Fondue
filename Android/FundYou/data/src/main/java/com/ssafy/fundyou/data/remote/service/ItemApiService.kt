package com.ssafy.fundyou.data.remote.service

import com.ssafy.fundyou.data.remote.datasource.item.dto.ItemResponseDto
import com.ssafy.fundyou.data.remote.datasource.item.dto.ItemSearchRequestDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

internal interface ItemApiService {
    @GET("/item/list")
    suspend fun getAllProductItemList() : List<ItemResponseDto>

    @GET("/item/ranking")
    suspend fun getRankingItemList() : List<ItemResponseDto>

    @GET("/item/random")
    suspend fun getRandomItemList() : List<ItemResponseDto>

    @GET("/item/favorite")
    suspend fun getFavoriteItemList() : List<ItemResponseDto>

    @GET("/item/category/{categoryId}")
    suspend fun getCategoryItemList(categoryId: Int) : List<ItemResponseDto>

    @POST("/search")
    suspend fun getKeywordItemList(@Body request : ItemSearchRequestDto) : List<ItemResponseDto>
}