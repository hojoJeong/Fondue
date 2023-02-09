package com.ssafy.fundyou.domain.repository

import com.ssafy.fundyou.domain.model.item.ProductItemModel

interface ItemRepository {
    suspend fun getAllProductItemList() : List<ProductItemModel>
    suspend fun getRankingItemList(categoryId: Int, minPrice: Int, maxPrice: Int) : List<ProductItemModel>
    suspend fun getRandomItemList() : List<ProductItemModel>
    suspend fun getLikeItemList() : List<ProductItemModel>
    suspend fun getCategoryItemList(categoryId: Int) : List<ProductItemModel>
    suspend fun getItemByPrice(categoryId: Int, minPrice: Int, maxPrice: Int): List<ProductItemModel>
    suspend fun getKeywordItemList(keyword : String, minPrice : Int, maxPrice : Int) : List<ProductItemModel>
    suspend fun addLikeItem(itemId: Long)
}