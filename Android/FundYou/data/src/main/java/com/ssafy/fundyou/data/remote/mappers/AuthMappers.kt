package com.ssafy.fundyou.data.remote.mappers

import com.ssafy.fundyou.data.remote.datasource.auth.dto.AuthResponseDto
import com.ssafy.fundyou.domain.model.auth.JWTAuthModel

internal fun AuthResponseDto.toDomainModel() = JWTAuthModel(
    accessToken = this.data?.accessToken ?: "",
    refreshToken = this.data?.refreshToken ?: "",
    statusMessage = this.message ?: ""
)