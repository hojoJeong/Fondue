package com.ssafy.fundyou.domain.usecase.ar

import com.ssafy.fundyou.domain.repository.ArRepository
import javax.inject.Inject

class SaveArImageUseCase @Inject constructor(private val arRepository: ArRepository) {
    suspend operator fun invoke(fundingItemId:Long, url: String) = arRepository.saveArImage(fundingItemId, url)
}