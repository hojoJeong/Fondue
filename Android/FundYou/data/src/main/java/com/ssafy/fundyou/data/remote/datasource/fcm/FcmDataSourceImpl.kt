package com.ssafy.fundyou.data.remote.datasource.fcm

import com.ssafy.fundyou.data.remote.datasource.BaseResultDto
import com.ssafy.fundyou.data.remote.service.FcmApiService
import javax.inject.Inject

internal class FcmDataSourceImpl @Inject constructor(private val fcmApiService: FcmApiService) : FcmDataSource {
    override suspend fun getNotificationSettingStatus(): BaseResultDto = fcmApiService.getNotificationSetting()

    override suspend fun updateNotificationSettingStatus() {
        fcmApiService.updateNotificationSetting()
    }

    override suspend fun addFcmToken(fcmTokenRequestDto: FcmTokenRequestDto) {
        fcmApiService.addFcmToken(fcmTokenRequestDto)
    }
}