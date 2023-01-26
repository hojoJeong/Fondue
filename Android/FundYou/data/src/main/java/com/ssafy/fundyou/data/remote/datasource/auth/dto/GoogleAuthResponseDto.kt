package com.ssafy.fundyou.data.remote.datasource.auth.dto

data class GoogleAuthResponseDto(
    var accessToken: String?,
    var expiresIn: Int?,
    var scope: String?,
    var tokenType: String?,
    var idToken: String?,
)
