package com.ssafy.fundyou.data.remote.datasource.auth

import com.ssafy.fundyou.data.remote.datasource.auth.dto.KakaoAuthResponseDto
import com.ssafy.fundyou.data.remote.service.AuthApiService
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authApiService: AuthApiService
) : AuthRemoteDataSource {
    override suspend fun getJWTByKakao(accessToken: String): KakaoAuthResponseDto {
        return authApiService.getJWTByKakao(accessToken)
    }
}