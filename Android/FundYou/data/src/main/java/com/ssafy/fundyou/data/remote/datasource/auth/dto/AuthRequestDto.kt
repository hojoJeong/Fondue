package com.ssafy.fundyou.data.remote.datasource.auth.dto

import com.google.gson.annotations.SerializedName

data class AuthRequestDto(
    @SerializedName("accessToken")
    val accessToken: String?,
    @SerializedName("refreshToken")
    val refreshToken: String?
)