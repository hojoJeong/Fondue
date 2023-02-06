package com.ssafy.fundyou.data.remote.mappers.item

import com.ssafy.fundyou.data.remote.datasource.item.dto.ItemResponseDto
import com.ssafy.fundyou.domain.model.item.ProductItemModel

//API 문서 확인 후 수정
internal fun MutableList<ItemResponseDto>.toDomainModel() = mutableListOf<ProductItemModel>()
