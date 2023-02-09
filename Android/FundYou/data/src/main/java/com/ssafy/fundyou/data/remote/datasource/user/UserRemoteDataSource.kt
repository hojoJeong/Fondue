package com.ssafy.fundyou.data.remote.datasource.user

import com.ssafy.fundyou.data.remote.datasource.user.dto.UserResponseDto

internal interface UserRemoteDataSource {
    suspend fun getUserInfo() : UserResponseDto
}