package com.ssafy.fundyou.domain.repository

import android.util.proto.ProtoOutputStream
import com.ssafy.fundyou.domain.model.item.ProductItemModel

interface ItemRepository {
    suspend fun getAllProductItemList() : List<ProductItemModel>
    suspend fun getRankingItemList() : List<ProductItemModel>
    suspend fun getRandomItemList() : List<ProductItemModel>
    suspend fun getFavoriteItemList() : List<ProductItemModel>
    suspend fun getCategoryItemList(categoryId: Int) : List<ProductItemModel>
    suspend fun getItemByPrice(categoryId: Int, minPrice: Int, maxPrice: Int): List<ProductItemModel>
    suspend fun getKeywordItemList(keyword : String, minPrice : Int, maxPrice : Int) : List<ProductItemModel>
}