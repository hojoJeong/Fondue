package com.ssafy.fundyou.data.remote.repository

import com.ssafy.fundyou.data.remote.datasource.user.UserRemoteDataSource
import com.ssafy.fundyou.data.remote.mappers.toDomainModel
import com.ssafy.fundyou.domain.model.user.UserInfoModel
import com.ssafy.fundyou.domain.repository.UserRepository
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(private val userRemoteDataSource: UserRemoteDataSource) : UserRepository {
    override suspend fun getUserInfo(): UserInfoModel = userRemoteDataSource.getUserInfo().toDomainModel()
}