package com.ssafy.fundyou.data.remote.datasource.auth.dto

import com.google.gson.annotations.SerializedName

data class GoogleAuthRequestDto(
    @SerializedName("grant_type")
    private val grantType: String,
    @SerializedName("client_id")
    private val clientId: String,
    @SerializedName("client_secret")
    private val clientSecret: String,
    @SerializedName("redirect_uri")
    private val redirectUri: String,
    @SerializedName("code")
    private val code: String
)