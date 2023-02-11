package com.ssafy.fundyou.data.remote.service

import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingCreateRequestDto
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

internal interface FundingApiService {
    @POST("/funding")
    suspend fun getFundingInfo(
        @Body fundingId : Long
    ) : FundingResponseDto

    @POST("/funding/create")
    suspend fun createFunding(
        @Body endDate : FundingCreateRequestDto
    ) : Long
}