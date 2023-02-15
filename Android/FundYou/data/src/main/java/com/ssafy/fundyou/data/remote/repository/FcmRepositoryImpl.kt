package com.ssafy.fundyou.data.remote.repository

import com.ssafy.fundyou.data.remote.datasource.fcm.FcmDataSource
import com.ssafy.fundyou.data.remote.datasource.fcm.FcmTokenRequestDto
import com.ssafy.fundyou.domain.repository.FcmRepository
import javax.inject.Inject

internal class FcmRepositoryImpl @Inject constructor(private val fcmDataSource: FcmDataSource) :
    FcmRepository {
    override suspend fun getNotificationSettingStatus(): Int =
        fcmDataSource.getNotificationSettingStatus().data

    override suspend fun updateNotificationSettingStatus() {
        fcmDataSource.updateNotificationSettingStatus()
    }

    override suspend fun addFcmToken(fcmToken: String) {
        val request = FcmTokenRequestDto(fcmToken)
        fcmDataSource.addFcmToken(request)
    }

}