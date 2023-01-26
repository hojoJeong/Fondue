package com.ssafy.fundyou.data.remote.datasource.auth

import com.ssafy.fundyou.data.remote.datasource.auth.dto.GoogleAuthResponseDto
import com.ssafy.fundyou.domain.model.GoogleAuthModel

interface AuthRemoteDataSource {
    suspend fun postAuthTokenWithGoogle(
        authCode : String,
        clientId : String,
        clientSecretId : String
    ) : GoogleAuthResponseDto
}