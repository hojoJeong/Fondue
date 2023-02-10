package com.ssafy.fundyou.domain.usecase.user

import com.ssafy.fundyou.domain.repository.UserRepository
import javax.inject.Inject

class LoadPointUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(point: Int) = userRepository.loadPoint(point)
}