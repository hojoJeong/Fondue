package com.ssafy.fundyou.domain.model.auth

data class JWTAuthModel(
    val accessToken : String,
    val refreshToken : String
)