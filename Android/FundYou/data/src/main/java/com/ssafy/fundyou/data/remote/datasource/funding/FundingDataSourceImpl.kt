package com.ssafy.fundyou.data.remote.datasource.funding

import com.ssafy.fundyou.data.remote.datasource.funding.dto.*
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingCreateRequestDto
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingInfoRequestDto
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingItemResponseDto
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingResponseDto
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingSimpleResponseDto
import com.ssafy.fundyou.data.remote.service.FundingApiService
import javax.inject.Inject

internal class FundingDataSourceImpl @Inject constructor(
    private val fundingApiService: FundingApiService
) : FundingDataSource {
    override suspend fun getFundingInfo(fundingInfoRequestDto: FundingInfoRequestDto) =
        fundingApiService.getFundingInfo(fundingInfoRequestDto)

    override suspend fun createFunding(endDate: FundingCreateRequestDto) =
        fundingApiService.createFunding(endDate)

    override suspend fun getMyOngoingFunding(): List<FundingResponseDto> =
        fundingApiService.getMyOngoingFunding()

    override suspend fun getMyClosedFunding() = fundingApiService.getMyClosedFundingList()
    override suspend fun getFundingItemList(fundingInfoRequestDto: FundingInfoRequestDto) =
        fundingApiService.getFundingItemList(fundingInfoRequestDto)
}