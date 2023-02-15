package com.ssafy.fundyou.data.remote.datasource.auth

import com.ssafy.fundyou.data.remote.datasource.auth.dto.AuthRequestDto
import com.ssafy.fundyou.data.remote.datasource.auth.dto.AuthResponseDto
import com.ssafy.fundyou.data.remote.datasource.auth.dto.AuthSuccessResponseDto
import com.ssafy.fundyou.data.remote.service.AuthApiService
import javax.inject.Inject

internal class AuthRemoteDataSourceImpl @Inject constructor(
    private val authApiService: AuthApiService
) : AuthRemoteDataSource {
    override suspend fun getJWTByKakao(accessToken: String): AuthResponseDto {
        return authApiService.getJWTByKakao(accessToken)
    }

    override suspend fun getJWTByRefreshToken(authRequestDto: AuthRequestDto): AuthResponseDto {
        return authApiService.getJWTByRefreshToken(authRequestDto)
    }
}