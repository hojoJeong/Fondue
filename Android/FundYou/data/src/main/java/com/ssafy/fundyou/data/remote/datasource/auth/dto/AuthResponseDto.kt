package com.ssafy.fundyou.data.remote.datasource.auth.dto

import com.google.gson.annotations.SerializedName

data class AuthResponseDto(
    @SerializedName("accessToken")
    var accessToken : String?,
    @SerializedName("accessTokenExpiresIn")
    var accessTokenExpiresIn : Long?,
    @SerializedName("grantType")
    var grantType : String?,
    @SerializedName("refreshToken")
    var refreshToken : String?
)
