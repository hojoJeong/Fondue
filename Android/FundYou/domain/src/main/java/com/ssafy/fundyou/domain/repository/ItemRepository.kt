package com.ssafy.fundyou.domain.repository

import com.ssafy.fundyou.domain.model.item.ProductItemModel

interface ItemRepository {
    suspend fun getAllProductItemList() : List<ProductItemModel>
    suspend fun getRankingItemList() : List<ProductItemModel>
    suspend fun getRandomItemList() : List<ProductItemModel>
    suspend fun getFavoriteItemList() : List<ProductItemModel>
}