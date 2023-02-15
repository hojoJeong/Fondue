package com.ssafy.fundyou.domain.usecase.fcm

import com.ssafy.fundyou.domain.repository.FcmRepository
import javax.inject.Inject

class GetNotificationSettingStatusUseCase @Inject constructor(private val fcmRepository: FcmRepository) {
    suspend operator fun invoke() = fcmRepository.getNotificationSettingStatus()
}