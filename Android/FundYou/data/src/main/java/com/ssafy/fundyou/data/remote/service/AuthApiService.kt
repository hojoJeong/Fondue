package com.ssafy.fundyou.data.remote.service

import com.ssafy.fundyou.data.remote.datasource.auth.dto.KakaoAuthResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("/auth/members/social/kakao")
    suspend fun getJWTByKakao(
        @Body accessToken: String
    ): KakaoAuthResponseDto
}