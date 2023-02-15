package com.ssafy.fundyou.domain.repository

interface FcmRepository {
    suspend fun getNotificationSettingStatus() : Int
    suspend fun updateNotificationSettingStatus()
    suspend fun addFcmToken(fcmToken: String)
}