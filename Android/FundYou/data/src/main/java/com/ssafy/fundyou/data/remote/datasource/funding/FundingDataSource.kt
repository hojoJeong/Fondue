package com.ssafy.fundyou.data.remote.datasource.funding

import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingResponseDto

internal interface FundingDataSource {
    suspend fun getFundingInfo(fundingId : Long) : FundingResponseDto
    suspend fun createFunding(endDate : Long) : Long
}