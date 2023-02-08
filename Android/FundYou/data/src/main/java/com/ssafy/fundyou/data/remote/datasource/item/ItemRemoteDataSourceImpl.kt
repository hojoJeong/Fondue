package com.ssafy.fundyou.data.remote.datasource.item

import com.ssafy.fundyou.data.remote.datasource.item.dto.ItemResponseDto
import com.ssafy.fundyou.data.remote.service.ItemApiService
import javax.inject.Inject

internal class ItemRemoteDataSourceImpl @Inject constructor(private var itemApiService: ItemApiService) :
    ItemRemoteDataSource {
    override suspend fun getAllItemList(): List<ItemResponseDto> =
        itemApiService.getAllProductItemList()

    override suspend fun getRankingItemList(
        categoryId: Int,
        minPrice: Int,
        maxPrice: Int
    ): List<ItemResponseDto> = itemApiService.getRankingItemList(categoryId, minPrice, maxPrice)

    override suspend fun getRandomItemList(): List<ItemResponseDto> =
        itemApiService.getRandomItemList()

    override suspend fun getFavoriteItemList(): List<ItemResponseDto> =
        itemApiService.getFavoriteItemList()

    override suspend fun getCategoryItemList(categoryId: Int): List<ItemResponseDto> =
        itemApiService.getCategoryItemList(categoryId)

    override suspend fun getItemByPrice(
        categoryId: Int,
        minPrice: Int,
        maxPrice: Int
    ): List<ItemResponseDto> = itemApiService.getItemByPrice(categoryId, minPrice, maxPrice)
}