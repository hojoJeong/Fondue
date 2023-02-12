package com.ssafy.fundyou.data.remote.service

import com.ssafy.fundyou.data.remote.datasource.funding.dto.*
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingCreateRequestDto
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingResponseDto
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingSimpleResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

internal interface FundingApiService {
    @POST("/funding")
    suspend fun getFundingInfo(
        @Body fundingId: FundingIdRequestDto
    ): FundingSimpleResponseDto

    @POST("/funding/create")
    suspend fun createFunding(
        @Body fundingId: FundingCreateRequestDto
    ): Long

    @POST("/funding/myOngoingList")
    suspend fun getMyOngoingFunding(): List<FundingResponseDto>

    @POST("/funding/myClosedList")
    suspend fun getMyClosedFundingList(): List<FundingResponseDto>

    @POST("/fundingItem/list")
    suspend fun getFundingItemList(
        @Body fundingId: FundingIdRequestDto
    ): List<FundingItemResponseDto>

    @POST("/fundingItem/terminate")
    suspend fun terminateFundingItem(
        @Body fundingItemId: FundingItemIdRequestDto
    ): Boolean

    @POST("/funding/statistics")
    suspend fun getFundingStatistics(
        @Body fundingId: FundingIdRequestDto
    ) : List<FundingStatisticsResponseDto>
}