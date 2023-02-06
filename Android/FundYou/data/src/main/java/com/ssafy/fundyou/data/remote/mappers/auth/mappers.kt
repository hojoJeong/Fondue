package com.ssafy.fundyou.data.remote.mappers.auth

import com.ssafy.fundyou.data.remote.datasource.auth.dto.AuthResponseDto
import com.ssafy.fundyou.domain.model.auth.JWTAuthModel

internal fun AuthResponseDto.toDomainModel() = JWTAuthModel(
    accessToken = this.accessToken ?: "",
    refreshToken = this.refreshToken ?: ""
)