package com.ssafy.fundyou.data.remote.service

import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingCreateRequestDto
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingItemResponseDto
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingResponseDto
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingSimpleResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

internal interface FundingApiService {
    @POST("/funding")
    suspend fun getFundingInfo(
        @Body fundingId : Long
    ) : FundingSimpleResponseDto

    @POST("/funding/create")
    suspend fun createFunding(
        @Body endDate : FundingCreateRequestDto
    ) : Long

    @POST("/funding/myOngoingList")
    suspend fun getMyOngoingFunding() : List<FundingResponseDto>

    @POST("/funding/myClosedList")
    suspend fun getMyClosedFundingList() : List<FundingResponseDto>
}