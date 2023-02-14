package com.ssafy.fundyou.data.remote.repository

import com.ssafy.fundyou.data.remote.datasource.pay.PayRemoteDataSource
import com.ssafy.fundyou.data.remote.datasource.pay.dto.PayAttendRequestDto
import com.ssafy.fundyou.data.remote.mappers.toDomainModel
import com.ssafy.fundyou.domain.model.pay.PayAttendModel
import com.ssafy.fundyou.domain.repository.PayRepository
import javax.inject.Inject

internal class PayRepositoryImpl @Inject constructor(
    private val payRemoteDataSource: PayRemoteDataSource
) : PayRepository{
    override suspend fun attendFundingItem(
        fundingItemId: Long,
        message: String,
        point: Int
    ): PayAttendModel {
        val request = PayAttendRequestDto(fundingItemId,message,point)
        val response = payRemoteDataSource.attendFundingItem(request)
        return response.toDomainModel()
    }
}