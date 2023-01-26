package com.ssafy.fundyou.domain.repository

import com.ssafy.fundyou.domain.model.GoogleAuthModel

interface AuthRepository {
    suspend fun getGoogleAuthToken(authCode : String, clientId : String, clientSecretId : String) : GoogleAuthModel
}