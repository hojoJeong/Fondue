package com.ssafy.fundyou.data.remote.datasource.funding

import com.ssafy.fundyou.data.remote.datasource.funding.dto.*
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingCreateRequestDto
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingIdRequestDto
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingItemResponseDto
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingResponseDto
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingSimpleResponseDto

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
    suspend fun saveFundingInfo(fundingIdRequestDto: FundingIdRequestDto) : FundingSaveResponseDto
    suspend fun getFundingParticipateList(status: Int) : List<FundingParticipateResponseDto>
}