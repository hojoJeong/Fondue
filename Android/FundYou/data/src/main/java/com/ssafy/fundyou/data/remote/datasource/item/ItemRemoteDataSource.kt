package com.ssafy.fundyou.data.remote.datasource.item

import com.ssafy.fundyou.data.remote.datasource.item.dto.ItemResponseDto

interface ItemRemoteDataSource {
    suspend fun getItemList() : MutableList<ItemResponseDto>
}