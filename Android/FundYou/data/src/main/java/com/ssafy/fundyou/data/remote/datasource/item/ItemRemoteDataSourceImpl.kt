package com.ssafy.fundyou.data.remote.datasource.item

import com.ssafy.fundyou.data.remote.datasource.item.dto.ItemResponseDto
import com.ssafy.fundyou.data.remote.service.ItemApiService
import javax.inject.Inject

internal class ItemRemoteDataSourceImpl @Inject constructor(private val itemApiService: ItemApiService) : ItemRemoteDataSource {
    override suspend fun getAllItemList(): List<ItemResponseDto> = itemApiService.getProductItemList()
    override suspend fun getRandomItemList(): List<ItemResponseDto> = itemApiService.getRandomItemList()
}