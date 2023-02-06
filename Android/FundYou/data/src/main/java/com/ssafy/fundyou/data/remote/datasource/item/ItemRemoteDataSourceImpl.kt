package com.ssafy.fundyou.data.remote.datasource.item

import com.ssafy.fundyou.data.remote.datasource.item.dto.ItemResponseDto
import com.ssafy.fundyou.data.remote.service.ItemApiService
import javax.inject.Inject

class ItemRemoteDataSourceImpl @Inject constructor(private val itemApiService: ItemApiService) : ItemRemoteDataSource {
    override suspend fun getItemList(): MutableList<ItemResponseDto> = itemApiService.getItemList()
}