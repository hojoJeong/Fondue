package com.ssafy.fundyou.data.remote.datasource.auth

import com.ssafy.fundyou.data.remote.datasource.auth.dto.KakaoAuthResponseDto
import com.ssafy.fundyou.domain.model.auth.JWTAuthModel


interface AuthRemoteDataSource {
    suspend fun getJWTByKakao(
        accessToken : String
    ) : KakaoAuthResponseDto
}