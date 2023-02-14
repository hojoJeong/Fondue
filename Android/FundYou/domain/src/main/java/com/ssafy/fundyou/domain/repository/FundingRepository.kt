package com.ssafy.fundyou.domain.repository

import com.ssafy.fundyou.domain.model.funding.*

interface FundingRepository {
    suspend fun getFundingInfo(fundingId : Long) : FundingInfoModel
    suspend fun createFunding(endDate : Long, fundingName : String) : Long
    suspend fun getMyOngoingFunding() : List<FundingTotalModel>
    suspend fun getMyClosedFunding() : List<FundingTotalModel>
    suspend fun getFundingItemList(fundingId : Long) : List<FundingItemInfoModel>
    suspend fun terminateFundingItem(fundingItemId : Long) : Boolean
    suspend fun getFundingStatistics(fundingId : Long) : List<FundingStatisticsModel>
    suspend fun getFundingItem(fundingItemId : Long) : FundingItemInfoModel
    suspend fun getFundingParticipateMessageList(fundingItemId : Long) : List<FundingMessageModel>
    suspend fun addOngoingFundingItem() : Long
    suspend fun getFundingHostInfo(fundingId : Long) : FundingHostInfoModel
}