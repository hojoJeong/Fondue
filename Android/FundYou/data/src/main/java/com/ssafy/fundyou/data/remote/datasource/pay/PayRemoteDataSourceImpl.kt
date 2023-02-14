package com.ssafy.fundyou.data.remote.datasource.pay

import com.ssafy.fundyou.data.remote.datasource.pay.dto.PayAttendRequestDto
import com.ssafy.fundyou.data.remote.datasource.pay.dto.PayAttendResponseDto
import com.ssafy.fundyou.data.remote.service.PayApiService
import javax.inject.Inject

internal class PayRemoteDataSourceImpl @Inject constructor(
    private val payApiService: PayApiService
) : PayRemoteDataSource{
    override suspend fun attendFundingItem(request: PayAttendRequestDto): PayAttendResponseDto {
        return payApiService.attendFundingItem(request)
    }
}