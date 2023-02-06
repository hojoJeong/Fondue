package com.ssafy.fundyou.data.remote.service

import com.ssafy.fundyou.data.remote.datasource.item.dto.ItemResponseDto
import retrofit2.http.GET

interface ItemApiService {
    @GET("/item/list")
    suspend fun getItemList() : MutableList<ItemResponseDto>
}