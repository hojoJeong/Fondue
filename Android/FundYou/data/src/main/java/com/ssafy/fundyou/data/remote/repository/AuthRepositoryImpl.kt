package com.ssafy.fundyou.data.remote.repository

import com.ssafy.fundyou.data.local.prefs.AuthSharePreference
import com.ssafy.fundyou.data.remote.datasource.auth.AuthRemoteDataSource
import com.ssafy.fundyou.data.remote.datasource.auth.dto.AuthRequestDto
import com.ssafy.fundyou.data.remote.datasource.auth.dto.AuthResponseDto
import com.ssafy.fundyou.data.remote.mappers.toDomainModel
import com.ssafy.fundyou.domain.model.auth.JWTAuthModel
import com.ssafy.fundyou.domain.repository.AuthRepository
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authSharePreference: AuthSharePreference
) : AuthRepository{
    override suspend fun getJWTByKakao(accessToken: String): JWTAuthModel {
        val response = authRemoteDataSource.getJWTByKakao(accessToken)
        saveJWT(response)

        return response.toDomainModel()
    }

    override suspend fun getAccessToken(): String {
        return authSharePreference.accessToken ?: ""
    }

    override suspend fun getRefreshToken(): String {
        return authSharePreference.refreshToken ?: ""
    }

    override suspend fun getJWTByRefreshToken(): JWTAuthModel {
        val request = AuthRequestDto(
            accessToken = authSharePreference.accessToken,
            refreshToken = authSharePreference.refreshToken,
        )
        val response = authRemoteDataSource.getJWTByRefreshToken(request)
        saveJWT(response)

        return response.toDomainModel()
    }

    private fun saveJWT(authResponseDto: AuthResponseDto){
        authSharePreference.accessToken = authResponseDto.accessToken
        authSharePreference.refreshToken = authResponseDto.refreshToken
    }
}