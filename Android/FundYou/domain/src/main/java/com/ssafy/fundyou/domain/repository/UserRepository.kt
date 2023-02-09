package com.ssafy.fundyou.domain.repository

import com.ssafy.fundyou.domain.model.user.UserInfoModel

interface UserRepository {
    suspend fun getUserInfo(): UserInfoModel
}