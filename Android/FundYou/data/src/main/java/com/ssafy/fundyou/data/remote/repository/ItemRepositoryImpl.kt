package com.ssafy.fundyou.data.remote.repository

import com.ssafy.fundyou.data.remote.datasource.item.ItemRemoteDataSource
import com.ssafy.fundyou.data.remote.mappers.item.toAllItemModel
import com.ssafy.fundyou.domain.model.item.ProductItemModel
import com.ssafy.fundyou.domain.repository.ItemRepository
import javax.inject.Inject

internal class ItemRepositoryImpl : ItemRepository {
    @Inject lateinit var itemRemoteDataSource : ItemRemoteDataSource

    override suspend fun getAllProductItemList() =
        itemRemoteDataSource.getAllItemList().map { it.toAllItemModel() }

    override suspend fun getRankingItem(): List<ProductItemModel> = itemRemoteDataSource.getRankingItem().map { it.toAllItemModel() }

    override suspend fun getRandomItem(): List<ProductItemModel> = itemRemoteDataSource.getRandomItemList().map { it.toAllItemModel() }
}