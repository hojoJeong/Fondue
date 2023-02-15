package com.ssafy.fundyou.data.remote.service

import com.ssafy.fundyou.data.remote.datasource.auth.dto.AuthRequestDto
import com.ssafy.fundyou.data.remote.datasource.auth.dto.AuthResponseDto
import com.ssafy.fundyou.data.remote.datasource.auth.dto.AuthSuccessResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

internal interface AuthApiService {
    @POST("/auth/social/kakao")
    suspend fun getJWTByKakao(
        @Body accessToken: String
    ): AuthResponseDto

    @POST("/auth/reissue")
    suspend fun getJWTByRefreshToken(
        @Body authRequestDto : AuthRequestDto
    ) : AuthResponseDto
}