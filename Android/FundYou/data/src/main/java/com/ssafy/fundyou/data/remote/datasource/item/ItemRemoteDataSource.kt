package com.ssafy.fundyou.data.remote.datasource.item

import com.ssafy.fundyou.data.remote.datasource.item.dto.ItemResponseDto
import com.ssafy.fundyou.data.remote.datasource.item.dto.ItemSearchRequestDto

internal interface ItemRemoteDataSource {
    suspend fun getAllItemList(): List<ItemResponseDto>
    suspend fun getRankingItemList(): List<ItemResponseDto>
    suspend fun getRandomItemList(): List<ItemResponseDto>
    suspend fun getFavoriteItemList(): List<ItemResponseDto>
    suspend fun getCategoryItemList(categoryId: Int): List<ItemResponseDto>
    suspend fun getKeywordItemList(request: ItemSearchRequestDto): List<ItemResponseDto>
    suspend fun getItemByPrice(categoryId: Int, minPrice: Int, maxPrice: Int) : List<ItemResponseDto>
}