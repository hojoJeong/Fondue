package com.ssafy.fundyou.domain.repository

import com.ssafy.fundyou.domain.model.funding.FundingInfoModel

interface FundingRepository {
    suspend fun getFundingInfo(fundingId : Long) : FundingInfoModel
    suspend fun createFunding(endDate : Long) : Long
}