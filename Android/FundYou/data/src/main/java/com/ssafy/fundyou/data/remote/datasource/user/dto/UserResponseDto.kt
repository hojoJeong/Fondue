package com.ssafy.fundyou.data.remote.datasource.user.dto

import com.google.gson.annotations.SerializedName

internal data class UserResponseDto(
    @SerializedName("id")
    val id: Long,

    @SerializedName("loginId")
    val loginId: String,

    @SerializedName("username")
    val username: String,

    @SerializedName("status")
    val status: Boolean,

    @SerializedName("profileImg")
    val profileImg: String,

    @SerializedName("point")
    val point: Int
)