package com.ssafy.fundyou.domain.usecase.funding

import com.ssafy.fundyou.domain.repository.FundingRepository
import javax.inject.Inject

class GetFundingItemUseCase @Inject constructor(
    private val fundingRepository: FundingRepository
) {
    suspend operator fun invoke(fundingItemId : Long) = fundingRepository.getFundingItem(fundingItemId)
}