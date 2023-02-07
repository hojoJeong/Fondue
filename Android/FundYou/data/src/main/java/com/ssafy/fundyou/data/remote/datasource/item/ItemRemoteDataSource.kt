package com.ssafy.fundyou.data.remote.datasource.item

import com.ssafy.fundyou.data.remote.datasource.item.dto.ItemResponseDto

internal interface ItemRemoteDataSource {
    suspend fun getAllItemList() : List<ItemResponseDto>
    suspend fun getRankingItem() : List<ItemResponseDto>
    suspend fun getRandomItemList() : List<ItemResponseDto>
}