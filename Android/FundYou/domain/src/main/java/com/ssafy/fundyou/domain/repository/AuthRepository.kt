package com.ssafy.fundyou.domain.repository

import com.ssafy.fundyou.domain.model.auth.JWTAuthModel

interface AuthRepository {
    suspend fun getJWTByKakao(accessToken : String) : JWTAuthModel
}

