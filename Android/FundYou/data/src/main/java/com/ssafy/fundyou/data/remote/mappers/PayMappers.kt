package com.ssafy.fundyou.data.remote.mappers

import com.ssafy.fundyou.data.remote.datasource.pay.dto.PayAttendResponseDto
import com.ssafy.fundyou.domain.model.item.ProductItemModel
import com.ssafy.fundyou.domain.model.pay.PayAttendModel

internal fun PayAttendResponseDto.toDomainModel() = PayAttendModel(
    statusMessage = this.message ?: "",
    fundingItemInfoModel = this.fundingItemResponse?.toDomainModel()
)