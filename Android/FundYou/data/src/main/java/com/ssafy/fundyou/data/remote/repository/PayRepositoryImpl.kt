package com.ssafy.fundyou.data.remote.repository

import com.ssafy.fundyou.data.remote.datasource.pay.PayRemoteDataSource
import com.ssafy.fundyou.domain.model.funding.FundingItemInfoModel
import com.ssafy.fundyou.domain.repository.PayRepository
import javax.inject.Inject

internal class PayRepositoryImpl @Inject constructor(
    private val payRemoteDataSource: PayRemoteDataSource
) : PayRepository{
    override suspend fun attendFundingItem(
        fundingItemId: Long,
        message: String,
        point: Int
    ): FundingItemInfoModel {
        TODO("Not yet implemented")
    }
}