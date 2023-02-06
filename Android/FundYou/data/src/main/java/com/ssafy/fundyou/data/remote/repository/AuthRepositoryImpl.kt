package com.ssafy.fundyou.data.remote.repository

import com.ssafy.fundyou.data.local.prefs.AuthSharePreference
import com.ssafy.fundyou.data.remote.datasource.auth.AuthRemoteDataSource
import com.ssafy.fundyou.data.remote.mappers.auth.toDomainModel
import com.ssafy.fundyou.domain.model.auth.JWTAuthModel
import com.ssafy.fundyou.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authSharePreference: AuthSharePreference
) : AuthRepository{
    override suspend fun getJWTByKakao(accessToken: String): JWTAuthModel {
        val response = authRemoteDataSource.getJWTByKakao(accessToken)
        authSharePreference.accessToken = response.accessToken
        authSharePreference.refreshToken = response.refreshToken

        return response.toDomainModel()
    }
}