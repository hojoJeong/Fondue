package com.ssafy.fundyou.data.remote.service

import com.ssafy.fundyou.data.remote.datasource.user.dto.UserResponseDto
import retrofit2.http.GET

internal interface UserApiService {
    @GET("/api/members/me")
    suspend fun getUserInfo() : UserResponseDto
}