package com.ssafy.fundyou.domain.usecase.funding

import com.ssafy.fundyou.domain.repository.FundingRepository
import javax.inject.Inject
import javax.inject.Singleton

class AddFundingUseCase @Inject constructor(
    private val fundingRepository: FundingRepository
) {
    suspend operator fun invoke(endData : Long) = fundingRepository.createFunding(endData)
}