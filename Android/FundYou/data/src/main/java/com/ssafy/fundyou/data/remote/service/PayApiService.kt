package com.ssafy.fundyou.data.remote.service

import com.ssafy.fundyou.data.remote.datasource.pay.dto.PayAttendRequestDto
import com.ssafy.fundyou.data.remote.datasource.pay.dto.PayAttendResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

internal interface PayApiService {
    @POST("fundingItem/attend")
    suspend fun attendFundingItem(
        @Body request : PayAttendRequestDto
    ) : PayAttendResponseDto
}