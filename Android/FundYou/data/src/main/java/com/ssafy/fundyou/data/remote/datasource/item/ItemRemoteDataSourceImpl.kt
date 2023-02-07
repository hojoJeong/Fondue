package com.ssafy.fundyou.data.remote.datasource.item

import com.ssafy.fundyou.data.remote.datasource.item.dto.ItemResponseDto
import com.ssafy.fundyou.data.remote.service.ItemApiService
import javax.inject.Inject

internal class ItemRemoteDataSourceImpl: ItemRemoteDataSource {
    @Inject lateinit var itemApiService: ItemApiService

    override suspend fun getAllItemList(): List<ItemResponseDto> = itemApiService.getAllProductItemList()
    override suspend fun getRankingItem(): List<ItemResponseDto> = itemApiService.getRankingItem()
    override suspend fun getRandomItemList(): List<ItemResponseDto> = itemApiService.getRandomItemList()
}