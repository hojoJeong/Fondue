package com.ssafy.fundyou.data.remote.mappers

import com.ssafy.fundyou.data.remote.datasource.user.dto.UserResponseDto
import com.ssafy.fundyou.domain.model.user.UserInfoModel

internal fun UserResponseDto.toDomainModel() =  UserInfoModel(
    id = this.id
)