package com.ssafy.fundyou.domain.usecase.user

import com.ssafy.fundyou.domain.repository.UserRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val userRepository: UserRepository){
    operator fun invoke() = userRepository.clearAuthPreference()
}