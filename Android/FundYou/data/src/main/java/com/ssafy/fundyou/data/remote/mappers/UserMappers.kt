package com.ssafy.fundyou.data.remote.mappers

import com.ssafy.fundyou.data.remote.datasource.user.dto.UserResponseDto
import com.ssafy.fundyou.domain.model.user.UserInfoDomainModel

internal fun UserResponseDto.toDomainModel() =  UserInfoDomainModel(
    id = this.id,
    loginId = this.loginId,
    userName = this.username,
    status = this.status,
    profileImg = this.profileImg,
    point = this.point,
    email = this.email
)