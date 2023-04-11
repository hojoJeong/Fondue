package com.ssafy.fundyou.domain.repository

import com.ssafy.fundyou.domain.model.user.UserInfoDomainModel

interface UserRepository {
    suspend fun getUserInfo(): UserInfoDomainModel
    suspend fun loadPoint(point: Int): Int
}