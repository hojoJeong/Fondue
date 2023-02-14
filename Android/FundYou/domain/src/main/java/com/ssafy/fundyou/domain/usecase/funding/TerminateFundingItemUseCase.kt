package com.ssafy.fundyou.domain.usecase.funding

import com.ssafy.fundyou.domain.repository.FundingRepository
import javax.inject.Inject

class TerminateFundingItemUseCase @Inject constructor(
    private val fundingRepository: FundingRepository
){
    suspend operator fun invoke(fundingItemId : Long) = fundingRepository.terminateFundingItem(fundingItemId)
}