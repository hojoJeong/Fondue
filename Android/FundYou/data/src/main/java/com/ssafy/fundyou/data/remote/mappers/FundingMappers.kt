package com.ssafy.fundyou.data.remote.mappers

import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingResponseDto
import com.ssafy.fundyou.domain.model.funding.FundingInfoModel

internal fun FundingResponseDto.toDomainModel() = FundingInfoModel(
    id = this.id ?: -1,
    fundingStatus = this.fundingStatus ?: false,
    endDate = this.endDate ?: -1,
    currentFundingPrice = this.currentFundingPrice ?: -1,
    totalPrice = this.totalPrice ?: 0,
    percentage = this.percentage?.toFloat() ?: 0f,
)