package com.ssafy.fundyou.data.remote.service

import com.ssafy.fundyou.data.remote.datasource.BaseResultDto
import com.ssafy.fundyou.data.remote.datasource.fcm.FcmTokenRequestDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

internal interface FcmApiService {
    @GET("/fcm")
    suspend fun getNotificationSetting(): BaseResultDto

    @PUT("/fcm")
    suspend fun updateNotificationSetting()

    @POST("/fcm")
    suspend fun addFcmToken(@Body token: FcmTokenRequestDto)
}