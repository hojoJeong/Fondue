package com.ssafy.fundyou.domain.usecase.auth

import com.ssafy.fundyou.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetRefreshTokenUseCase @Inject constructor(private val authRepository: AuthRepository){
    suspend operator fun invoke() = authRepository.getRefreshToken()
}