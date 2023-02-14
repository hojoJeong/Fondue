package com.ssafy.fundyou.data.remote.datasource.user

import com.ssafy.fundyou.data.remote.datasource.BaseResultDto
import com.ssafy.fundyou.data.remote.datasource.user.dto.UserResponseDto
import com.ssafy.fundyou.data.remote.service.UserApiService
import javax.inject.Inject

internal class UserRemoteDataSourceImpl @Inject constructor(private val userApiService: UserApiService) : UserRemoteDataSource{
    override suspend fun getUserInfo(): UserResponseDto = userApiService.getUserInfo()
    override suspend fun loadPoint(point: Int): Int = userApiService.loadPoint(point)
    override suspend fun withdrawalMembership() : BaseResultDto = userApiService.withdrawalMembership()
}