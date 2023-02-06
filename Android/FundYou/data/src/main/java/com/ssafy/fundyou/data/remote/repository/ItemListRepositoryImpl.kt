package com.ssafy.fundyou.data.remote.repository

import com.ssafy.fundyou.data.remote.datasource.item.ItemRemoteDataSource
import com.ssafy.fundyou.data.remote.mappers.item.toDomainModel
import com.ssafy.fundyou.domain.model.item.ProductItemModel
import com.ssafy.fundyou.domain.repository.ItemListRepository
import javax.inject.Inject

class ItemListRepositoryImpl @Inject constructor(private val itemRemoteDataSource: ItemRemoteDataSource) : ItemListRepository {
    override suspend fun getItemList(): MutableList<ProductItemModel> {
        val response = itemRemoteDataSource.getItemList()
        return response.toDomainModel()
    }
}