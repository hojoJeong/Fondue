package com.ssafy.fundyou.data.remote.datasource.auth

import com.ssafy.fundyou.data.remote.datasource.auth.dto.AuthRequestDto
import com.ssafy.fundyou.data.remote.datasource.auth.dto.AuthResponseDto
import com.ssafy.fundyou.data.remote.datasource.auth.dto.AuthSuccessResponseDto


internal interface AuthRemoteDataSource {
    suspend fun getJWTByKakao(
        accessToken: String
    ): AuthResponseDto

    suspend fun getJWTByRefreshToken(
        authRequestDto : AuthRequestDto
    ): AuthResponseDto
}