package com.ssafy.fundyou.data.remote.repository

import com.ssafy.fundyou.data.remote.datasource.auth.AuthRemoteDataSource
import com.ssafy.fundyou.data.remote.mappers.auth.toDomainModel
import com.ssafy.fundyou.domain.model.GoogleAuthModel
import com.ssafy.fundyou.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository{
    override suspend fun getGoogleAuthToken(
        authCode: String,
        clientId: String,
        clientSecretId: String
    ): GoogleAuthModel {
        val response = authRemoteDataSource.postAuthTokenWithGoogle(authCode, clientId, clientSecretId)
        return response.toDomainModel()
    }
}