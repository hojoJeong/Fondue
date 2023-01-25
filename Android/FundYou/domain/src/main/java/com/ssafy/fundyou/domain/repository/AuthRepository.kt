package com.ssafy.fundyou.domain.repository

interface AuthRepository {
    suspend fun getGoogleAuthToken(authCode : String)
}