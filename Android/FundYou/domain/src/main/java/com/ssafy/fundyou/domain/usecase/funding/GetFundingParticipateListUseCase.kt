package com.ssafy.fundyou.domain.usecase.funding

import com.ssafy.fundyou.domain.repository.FundingRepository
import javax.inject.Inject

class GetFundingParticipateListUseCase @Inject constructor(private val fundingRepository: FundingRepository){
    suspend operator fun invoke(status: Int) = fundingRepository.getFundingParticipateList(status)
}