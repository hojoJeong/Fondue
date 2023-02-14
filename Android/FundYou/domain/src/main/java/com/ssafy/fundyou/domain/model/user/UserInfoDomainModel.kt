package com.ssafy.fundyou.domain.model.user

data class UserInfoDomainModel(
    val id: Long,
    val loginId: String,
    val userName: String,
    val status: Boolean,
    val profileImg: String,
    val point: Int,
    val email: String
)