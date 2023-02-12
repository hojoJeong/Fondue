package com.ssafy.fundyou.domain.repository

import com.ssafy.fundyou.domain.model.funding.FundingInfoModel
import com.ssafy.fundyou.domain.model.funding.FundingItemInfoModel
import com.ssafy.fundyou.domain.model.funding.FundingStatisticsModel
import com.ssafy.fundyou.domain.model.funding.FundingTotalModel

interface FundingRepository {
    suspend fun getFundingInfo(fundingId : Long) : FundingInfoModel
    suspend fun createFunding(endDate : Long, fundingName : String) : Long
    suspend fun getMyOngoingFunding() : List<FundingTotalModel>
    suspend fun getMyClosedFunding() : List<FundingTotalModel>
    suspend fun getFundingItemList(fundingId : Long) : List<FundingItemInfoModel>
    suspend fun terminateFundingItem(fundingItemId : Long) : Boolean
    suspend fun getFundingStatistics(fundingId : Long) : List<FundingStatisticsModel>
}