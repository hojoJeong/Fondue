package com.ssafy.fundyou.data.remote.repository

import com.ssafy.fundyou.data.remote.datasource.item.ItemRemoteDataSource
import com.ssafy.fundyou.data.remote.mappers.item.toAllItemModel
import com.ssafy.fundyou.domain.repository.ItemRepository
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(private val itemRemoteDataSource: ItemRemoteDataSource) :
    ItemRepository {
    override suspend fun getAllProductItemList() = itemRemoteDataSource.getAllItemList().map { it.toAllItemModel() }
}