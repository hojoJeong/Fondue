package com.ssafy.fundyou.domain.repository

import com.ssafy.fundyou.domain.model.funding.FundingItemInfoModel
import com.ssafy.fundyou.domain.model.pay.PayAttendModel

interface PayRepository {
    suspend fun attendFundingItem(
        fundingItemId: Long,
        message: String,
        point: Int
    ): PayAttendModel
}