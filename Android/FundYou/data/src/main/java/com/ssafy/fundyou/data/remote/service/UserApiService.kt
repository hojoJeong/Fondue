package com.ssafy.fundyou.data.remote.service

import com.ssafy.fundyou.data.remote.datasource.BaseResultDto
import com.ssafy.fundyou.data.remote.datasource.user.dto.UserResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

internal interface UserApiService {
    @GET("/profile")
    suspend fun getUserInfo() : UserResponseDto

    @GET("/member/{point}")
    suspend fun loadPoint(@Path("point") point: Int) : Int

    @GET("/member/withdrawal")
    suspend fun withdrawalMembership() : BaseResultDto
}