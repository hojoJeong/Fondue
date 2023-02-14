package com.ssafy.fundyou.data.remote.service

import com.ssafy.fundyou.data.remote.datasource.funding.dto.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

internal interface  FundingApiService {
    @POST("/funding")
    suspend fun getFundingInfo(
        @Body fundingId: FundingIdRequestDto
    ): FundingSimpleResponseDto

    @POST("/funding/create")
    suspend fun createFunding(
        @Body endDate : FundingCreateRequestDto
    ) : Long

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

    @POST("/fundingItem")
    suspend fun getFundingItem(
        @Body fundingItemIdRequestDto: FundingItemIdRequestDto
    ) : FundingItemResponseDto

    @POST("/fundingItem/memberList")
    suspend fun getFundingParticipateList(
        @Body fundingItemIdRequestDto: FundingItemIdRequestDto
    ) : List<FundingItemParticipateResponseDto>

    @POST("/funding/add")
    suspend fun addOngoingFundingItem() : Long

    @POST("/funding/hostInfo")
    suspend fun getFundingHostInfo(
        @Body fundingId : FundingIdRequestDto
    ) : FundingHostInfoResponseDto

    @POST("/funding/getInvited")
    suspend fun saveFundingInfo(
        @Body fundingId : FundingIdRequestDto
    ) : FundingSaveResponseDto

    @GET("/funding/invitedList/{status}")
    suspend fun getFundingParticipateList(@Path("status") status: Int) : List<FundingParticipateResponseDto>
}