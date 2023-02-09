package com.ssafy.fundyou.data.remote.repository

import com.ssafy.fundyou.data.remote.datasource.item.ItemRemoteDataSource
import com.ssafy.fundyou.data.remote.datasource.item.dto.ItemSearchRequestDto
import com.ssafy.fundyou.data.remote.mappers.toDomainModel
import com.ssafy.fundyou.domain.model.item.ProductItemModel
import com.ssafy.fundyou.domain.repository.ItemRepository
import javax.inject.Inject

internal class ItemRepositoryImpl @Inject constructor(private var itemRemoteDataSource: ItemRemoteDataSource) :
    ItemRepository {
    override suspend fun getAllProductItemList() =
        itemRemoteDataSource.getAllItemList().map { it.toDomainModel() }

    override suspend fun getRankingItemList(categoryId: Int, minPrice: Int, maxPrice: Int) =
        itemRemoteDataSource.getRankingItemList(categoryId, minPrice, maxPrice)
            .map { it.toDomainModel() }

    override suspend fun getRandomItemList() =
        itemRemoteDataSource.getRandomItemList().map { it.toDomainModel() }

    override suspend fun getLikeItemList(): List<ProductItemModel> =
        itemRemoteDataSource.getLikeItemList().map { it.toDomainModel() }

    override suspend fun getCategoryItemList(categoryId: Int): List<ProductItemModel> =
        itemRemoteDataSource.getCategoryItemList(categoryId).map { it.toDomainModel() }

    override suspend fun getKeywordItemList(
        keyword: String,
        minPrice: Int,
        maxPrice: Int
    ): List<ProductItemModel> {
        val request = ItemSearchRequestDto(keyword, maxPrice, minPrice)
        return itemRemoteDataSource.getKeywordItemList(request).map { it.toDomainModel() }
    }

    override suspend fun addLikeItem(itemId: Long) = itemRemoteDataSource.addLikeItem(itemId)

    override suspend fun getItemByPrice(
        categoryId: Int,
        minPrice: Int,
        maxPrice: Int
    ): List<ProductItemModel> = itemRemoteDataSource.getItemByPrice(categoryId, minPrice, maxPrice)
        .map { it.toDomainModel() }
}