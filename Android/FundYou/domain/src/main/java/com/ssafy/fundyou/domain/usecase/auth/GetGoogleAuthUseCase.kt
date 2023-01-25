package com.ssafy.fundyou.domain.usecase.auth

import com.ssafy.fundyou.domain.repository.AuthRepository
import javax.inject.Inject

class GetGoogleAuthUseCase @Inject constructor(private val authRepository: AuthRepository){

}