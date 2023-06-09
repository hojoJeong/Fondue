package com.ssafy.fundyou.data.remote.repository

import com.ssafy.fundyou.data.local.prefs.AuthSharePreference
import com.ssafy.fundyou.data.remote.datasource.user.UserRemoteDataSource
import com.ssafy.fundyou.data.remote.mappers.toDomainModel
import com.ssafy.fundyou.domain.model.user.UserInfoDomainModel
import com.ssafy.fundyou.domain.repository.UserRepository
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val authSharePreference: AuthSharePreference
) : UserRepository {
    override suspend fun getUserInfo(): UserInfoDomainModel =
        userRemoteDataSource.getUserInfo().toDomainModel()

    override suspend fun loadPoint(point: Int): Int = userRemoteDataSource.loadPoint(point)
    override suspend fun withdrawalMembership(): Int =
        userRemoteDataSource.withdrawalMembership().statusCode

    override fun clearAuthPreference(): Boolean = authSharePreference.clearAuthPreference()

}