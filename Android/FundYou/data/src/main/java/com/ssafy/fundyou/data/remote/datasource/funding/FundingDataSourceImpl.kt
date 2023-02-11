package com.ssafy.fundyou.data.remote.datasource.funding

import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingResponseDto
import com.ssafy.fundyou.data.remote.service.FundingApiService
import javax.inject.Inject

internal class FundingDataSourceImpl @Inject constructor(
    private val fundingApiService: FundingApiService
) : FundingDataSource {
    override suspend fun getFundingInfo(fundingId: Long) = fundingApiService.getFundingInfo(fundingId)
    override suspend fun createFunding(endDate: Long) = fundingApiService.createFunding(endDate)
}