package com.ssafy.fundyou.domain.usecase.fcm

import com.ssafy.fundyou.domain.repository.FcmRepository
import javax.inject.Inject

class AddFcmTokenUseCase @Inject constructor(private val fcmRepository: FcmRepository) {
    suspend operator fun invoke(token: String) = fcmRepository.addFcmToken(token)
}