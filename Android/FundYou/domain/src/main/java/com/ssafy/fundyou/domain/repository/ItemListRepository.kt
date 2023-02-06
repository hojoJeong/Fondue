package com.ssafy.fundyou.domain.repository

import com.ssafy.fundyou.domain.model.item.ProductItemModel

interface ItemListRepository {
    suspend fun getItemList() : MutableList<ProductItemModel>
}