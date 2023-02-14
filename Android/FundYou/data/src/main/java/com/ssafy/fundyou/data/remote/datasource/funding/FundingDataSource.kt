package com.ssafy.fundyou.data.remote.datasource.funding

import com.ssafy.fundyou.data.remote.datasource.funding.dto.*

internal interface FundingDataSource {
    suspend fun getFundingInfo(fundingIdRequestDto: FundingIdRequestDto) : FundingSimpleResponseDto
    suspend fun createFunding(endDate : FundingCreateRequestDto) : Long
    suspend fun getMyOngoingFunding() : List<FundingResponseDto>
    suspend fun getMyClosedFunding() : List<FundingResponseDto>
    suspend fun getFundingItemList(fundingIdRequestDto: FundingIdRequestDto) : List<FundingItemResponseDto>
    suspend fun terminateFundingItem(fundingItemIdRequestDto: FundingItemIdRequestDto) : Boolean
    suspend fun getFundingStatisticsList(fundingIdRequestDto: FundingIdRequestDto) : List<FundingStatisticsResponseDto>
    suspend fun getFundingItem(fundingItemIdRequest : FundingItemIdRequestDto) : FundingItemResponseDto
    suspend fun getFundingItemParticipateList(fundingItemIdRequestDto: FundingItemIdRequestDto) : List<FundingItemParticipateResponseDto>
    suspend fun addOngoingFundingItem() : Long
    suspend fun getFundingHostInfo(fundingIdRequestDto: FundingIdRequestDto) : FundingHostInfoResponseDto
    suspend fun saveFundingInfo(fundingIdRequestDto: FundingIdRequestDto2) : FundingSaveResponseDto
    suspend fun getFundingParticipateList(status: Int) : List<FundingParticipateResponseDto>
}