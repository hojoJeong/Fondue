package com.ssafy.fundyou.data.remote.datasource.auth

import com.ssafy.fundyou.data.remote.datasource.auth.dto.AuthRequestDto
import com.ssafy.fundyou.data.remote.datasource.auth.dto.AuthResponseDto


interface AuthRemoteDataSource {
    suspend fun getJWTByKakao(
        accessToken: String
    ): AuthResponseDto

    suspend fun getJWTByRefreshToken(
        authRequestDto : AuthRequestDto
    ): AuthResponseDto
}