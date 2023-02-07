package com.ssafy.fundyou.domain.repository

import com.ssafy.fundyou.domain.model.item.ProductItemModel

interface ItemRepository {
    suspend fun getAllProductItemList() : List<ProductItemModel>
    suspend fun getRankingItem() : List<ProductItemModel>
    suspend fun getRandomItem() : List<ProductItemModel>
}