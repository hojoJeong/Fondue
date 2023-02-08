package com.ssafy.fundyou.data.remote.repository

import com.ssafy.fundyou.data.remote.datasource.item.ItemRemoteDataSource
import com.ssafy.fundyou.data.remote.mappers.item.toDomainModel
import com.ssafy.fundyou.domain.model.item.ProductItemModel
import com.ssafy.fundyou.domain.repository.ItemRepository
import javax.inject.Inject

internal class ItemRepositoryImpl @Inject constructor(private var itemRemoteDataSource: ItemRemoteDataSource) :
    ItemRepository {
    override suspend fun getAllProductItemList() =
        itemRemoteDataSource.getAllItemList().map { it.toDomainModel() }

    override suspend fun getRankingItemList(): List<ProductItemModel> =
        itemRemoteDataSource.getRankingItemList().map { it.toDomainModel() }

    override suspend fun getRandomItemList(): List<ProductItemModel> =
        itemRemoteDataSource.getRandomItemList().map { it.toDomainModel() }

    override suspend fun getFavoriteItemList(): List<ProductItemModel> =
        itemRemoteDataSource.getFavoriteItemList().map { it.toDomainModel() }

    override suspend fun getCategoryItemList(categoryId: Int): List<ProductItemModel> =
        itemRemoteDataSource.getCategoryItemList(categoryId).map { it.toDomainModel() }

    override suspend fun getKeywordItemList(
        keyword: String,
        minPrice: Int,
        maxPrice: Int
    ): List<ProductItemModel> {
        TODO("Not yet implemented")
    }
}