package com.ssafy.fundyou.domain.usecase.pay

import com.ssafy.fundyou.domain.repository.PayRepository
import javax.inject.Inject

class AttendFundingItemUseCase @Inject constructor(
    private val payRepository: PayRepository
) {
    suspend operator fun invoke(
        fundItemId: Long,
        message: String,
        point: Int
    ) = payRepository.attendFundingItem(fundItemId, message, point)
}