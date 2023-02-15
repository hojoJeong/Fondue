package com.ssafy.fundyou.data.remote.datasource.fcm

import com.ssafy.fundyou.data.remote.datasource.BaseResultDto

internal interface FcmDataSource {
    suspend fun getNotificationSettingStatus(): BaseResultDto
    suspend fun updateNotificationSettingStatus()
    suspend fun addFcmToken(fcmTokenRequestDto: FcmTokenRequestDto)
}