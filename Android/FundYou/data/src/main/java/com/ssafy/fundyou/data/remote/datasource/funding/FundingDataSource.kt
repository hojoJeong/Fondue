package com.ssafy.fundyou.data.remote.datasource.funding

import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingCreateRequestDto
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingResponseDto
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingSimpleResponseDto

internal interface FundingDataSource {
    suspend fun getFundingInfo(fundingId : Long) : FundingSimpleResponseDto
    suspend fun createFunding(endDate : FundingCreateRequestDto) : Long
    suspend fun getMyOngoingFunding() : List<FundingResponseDto>
    suspend fun getMyClosedFunding() : List<FundingResponseDto>
}