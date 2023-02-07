package com.ssafy.fundyou.data.remote.service

import com.ssafy.fundyou.data.remote.datasource.item.dto.ItemResponseDto
import retrofit2.http.GET

internal interface ItemApiService {
    @GET("/item/list")
    suspend fun getAllProductItemList() : List<ItemResponseDto>

    @GET("/item/ranking")
    suspend fun getRankingItemList() : List<ItemResponseDto>

    @GET("/item/random")
    suspend fun getRandomItemList() : List<ItemResponseDto>

    @GET("/item/")
    suspend fun getFavoriteItemList() : List<ItemResponseDto>
}