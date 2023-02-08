package com.ssafy.fundyou.data.remote.service

import com.ssafy.fundyou.data.remote.datasource.auth.dto.AuthRequestDto
import com.ssafy.fundyou.data.remote.datasource.auth.dto.AuthResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

internal interface AuthApiService {
    @POST("/auth/members/social/kakao")
    suspend fun getJWTByKakao(
        @Body accessToken: String
    ): AuthResponseDto

    @POST("/auth/members/reissue")
    suspend fun getJWTByRefreshToken(
        @Body authRequestDto : AuthRequestDto
    ) : AuthResponseDto
}