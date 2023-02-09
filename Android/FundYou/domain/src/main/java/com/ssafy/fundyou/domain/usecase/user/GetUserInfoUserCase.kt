package com.ssafy.fundyou.domain.usecase.user

import com.ssafy.fundyou.domain.repository.UserRepository
import javax.inject.Inject

class GetUserInfoUserCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke() = userRepository.getUserInfo()
}