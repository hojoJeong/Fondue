package com.ssafy.fundyou.data.remote.datasource.auth

import com.ssafy.fundyou.data.remote.datasource.auth.dto.GoogleAuthRequestDto
import com.ssafy.fundyou.data.remote.datasource.auth.dto.GoogleAuthResponseDto
import com.ssafy.fundyou.data.remote.service.AuthApiService
import com.ssafy.fundyou.domain.model.GoogleAuthModel
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authApiService: AuthApiService
) : AuthRemoteDataSource {
    override suspend fun postAuthTokenWithGoogle(
        authCode: String,
        clientId: String,
        clientSecretId: String
    ): GoogleAuthResponseDto {
        val request = GoogleAuthRequestDto(
            grantType = "authorization_code",
            clientId = clientId,
            clientSecret = clientSecretId,
            redirectUri = "",
            code = authCode
        )
        return authApiService.getGoogleAuthToken(request)
    }
}