package com.ssafy.fundyou.data.remote.datasource.funding

import com.ssafy.fundyou.data.remote.datasource.funding.dto.*
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingCreateRequestDto
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingIdRequestDto
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingResponseDto
import com.ssafy.fundyou.data.remote.service.FundingApiService
import javax.inject.Inject

internal class FundingDataSourceImpl @Inject constructor(
    private val fundingApiService: FundingApiService
) : FundingDataSource {
    override suspend fun getFundingInfo(fundingIdRequestDto: FundingIdRequestDto) =
        fundingApiService.getFundingInfo(fundingIdRequestDto)

    override suspend fun createFunding(endDate: FundingCreateRequestDto) =
        fundingApiService.createFunding(endDate)

    override suspend fun getMyOngoingFunding(): List<FundingResponseDto> =
        fundingApiService.getMyOngoingFunding()

    override suspend fun getMyClosedFunding() = fundingApiService.getMyClosedFundingList()

    override suspend fun getFundingItemList(fundingIdRequestDto: FundingIdRequestDto) =
        fundingApiService.getFundingItemList(fundingIdRequestDto)

    override suspend fun terminateFundingItem(fundingItemIdRequestDto: FundingItemIdRequestDto) =
        fundingApiService.terminateFundingItem(fundingItemIdRequestDto)

    override suspend fun getFundingStatisticsList(fundingIdRequestDto: FundingIdRequestDto) =
        fundingApiService.getFundingStatistics(fundingIdRequestDto)

    override suspend fun getFundingItem(fundingItemIdRequest: FundingItemIdRequestDto) =
        fundingApiService.getFundingItem(fundingItemIdRequest)

    override suspend fun getFundingItemParticipateList(fundingItemIdRequestDto: FundingItemIdRequestDto) =
        fundingApiService.getFundingParticipateList(fundingItemIdRequestDto)

    override suspend fun addOngoingFundingItem() = fundingApiService.addOngoingFundingItem()

    override suspend fun getFundingHostInfo(fundingIdRequestDto: FundingIdRequestDto) = fundingApiService.getFundingHostInfo(fundingIdRequestDto)
    override suspend fun saveFundingInfo(fundingIdRequestDto: FundingIdRequestDto) = fundingApiService.saveFundingInfo(fundingIdRequestDto)
    override suspend fun getFundingParticipateList(status: Int): List<FundingParticipateResponseDto> = fundingApiService.getFundingParticipateList(status)
}