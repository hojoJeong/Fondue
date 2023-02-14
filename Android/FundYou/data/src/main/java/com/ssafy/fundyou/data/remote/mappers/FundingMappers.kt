package com.ssafy.fundyou.data.remote.mappers

import com.ssafy.fundyou.data.remote.datasource.funding.dto.*
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingItemResponseDto
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingResponseDto
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingSimpleResponseDto
import com.ssafy.fundyou.domain.model.funding.*

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

internal fun FundingItemResponseDto.toDomainModel() = FundingItemInfoModel(
    id = this.id ?: -1,
    itemCount = count ?: 0,
    fundingHostUserName = this.fundingHostUserName ?: "",
    currentFundingPrice = currentFundingPrice ?: 0,
    status = fundingItemStatus ?: false,
    participantsCount = this.participateCount ?: 0,
    itemPrice = this.itemInfo.price * (this.count ?: 0),
    arImgList = this.arImgList ?: emptyList(),
    info = this.itemInfo.toDomainModel()
)

internal fun FundingStatisticsResponseDto.toDomainModel() = FundingStatisticsModel(
    fundingParticipateMemberId = this.memberId ?: -1,
    fundingParticipateMemberName = this.userName ?: "",
    fundingParticipateMemberProfileImg = this.profileImg ?: "",
    fundingPrice = this.attendedPrice ?: -1
)

internal fun FundingItemParticipateResponseDto.toDomainModel() = FundingMessageModel(
    id = this.id ?: -1,
    senderName = this.senderName ?: "",
    message = this.message ?: "",
    fundingPrice = this.fundingItemPrice ?: -1
)

internal fun FundingHostInfoResponseDto.toDomainModel() = FundingHostInfoModel(
    fundingName = this.fundingName ?: "",
    fundingHostName = this.hostName ?: "",
    fundingHostProfileImg = this.profileImg ?: ""
)