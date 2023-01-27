package com.ssafy.fundyou.data.remote.mappers.auth

import com.ssafy.fundyou.data.remote.datasource.auth.dto.GoogleAuthResponseDto
import com.ssafy.fundyou.domain.model.GoogleAuthModel

internal fun GoogleAuthResponseDto.toDomainModel() = GoogleAuthModel(
    accessToken = this.accessToken ?: ""
)