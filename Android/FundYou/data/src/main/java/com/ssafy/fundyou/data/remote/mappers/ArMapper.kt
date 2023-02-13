package com.ssafy.fundyou.data.remote.mappers

import com.ssafy.fundyou.data.remote.datasource.ar.dto.ArImageResponseDto
import com.ssafy.fundyou.domain.model.ar.ArImageModel

internal fun ArImageResponseDto.toDomainModel() = ArImageModel(
    fundingId = this.fundingId,
    arId = this.id,
    memberId = this.memberId,
    itemId = this.itemId,
    url = this.url
)