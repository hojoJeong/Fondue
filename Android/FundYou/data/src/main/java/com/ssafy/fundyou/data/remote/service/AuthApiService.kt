package com.ssafy.fundyou.data.remote.service

import com.ssafy.fundyou.data.remote.datasource.auth.dto.GoogleAuthRequestDto
import com.ssafy.fundyou.data.remote.datasource.auth.dto.GoogleAuthResponseDto
import com.ssafy.fundyou.domain.model.GoogleAuthModel
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("/o/oauth2/v4/token")
    suspend fun getGoogleAuthToken(
        @Body request : GoogleAuthRequestDto
    ) : GoogleAuthResponseDto
}