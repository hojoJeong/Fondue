package com.ssafy.fundyou.data.remote.datasource.pay

import com.ssafy.fundyou.data.remote.datasource.pay.dto.PayAttendRequestDto
import com.ssafy.fundyou.data.remote.datasource.pay.dto.PayAttendResponseDto

internal interface PayRemoteDataSource {
    suspend fun attendFundingItem(request : PayAttendRequestDto) : PayAttendResponseDto
}