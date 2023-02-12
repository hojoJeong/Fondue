package com.ssafy.fundyou.data.remote.mappers

import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingItemResponseDto
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingResponseDto
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingSimpleResponseDto
import com.ssafy.fundyou.domain.model.funding.FundingInfoModel
import com.ssafy.fundyou.domain.model.funding.FundingItemModel
import com.ssafy.fundyou.domain.model.funding.FundingTotalModel

internal fun FundingSimpleResponseDto.toDomainModel() = FundingInfoModel(
    id = this.id ?: -1,
    fundingName = this.fundingName ?: "",
    fundingStatus = this.fundingStatus ?: false,
    startDate = this.startDate ?: -1,
    endDate = this.endDate ?: -1,
    currentFundingPrice = this.currentFundingPrice ?: -1,
    totalPrice = this.totalPrice ?: 0,
    percentage = this.percentage ?: -1
)

internal fun FundingResponseDto.toDomainModel() = FundingTotalModel(
    fundingInfo = FundingInfoModel(
        id = this.id ?: -1,
        fundingName = this.fundingName,
        fundingStatus = this.fundingStatus ?: false,
        startDate = this.startDate ?: -1,
        endDate = this.endDate ?: -1,
        currentFundingPrice = this.currentFundingPrice ?: 0,
        totalPrice = this.totalPrice ?: 0,
        percentage = this.percentage ?: -1
    ),
    fundingItemList = this.fundingItemList?.map { it.toDomainModel() } ?: emptyList()
)

internal fun FundingItemResponseDto.toDomainModel() = FundingItemModel(
    id = this.id ?: -1,
    itemCount = count ?: 0,
    currentFundingPrice = currentFundingPrice ?: 0,
    status = fundingItemStatus ?: false,
    participantsCount = this.count ?: 0,
    itemPrice = this.itemInfo.price,
    info = this.itemInfo.toDomainModel()
)