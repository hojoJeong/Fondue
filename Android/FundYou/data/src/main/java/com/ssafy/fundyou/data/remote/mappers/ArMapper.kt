package com.ssafy.fundyou.data.remote.mappers

import com.ssafy.fundyou.data.remote.datasource.ar.dto.ArImageResponseDto
import com.ssafy.fundyou.domain.model.ar.ArImageModel

internal fun ArImageResponseDto.toDomainModel() = ArImageModel(
    fundingItemId = this.fundingItemId,
    arId = this.id,
    url = this.url
)