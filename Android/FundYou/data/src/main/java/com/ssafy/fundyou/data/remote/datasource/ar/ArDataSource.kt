package com.ssafy.fundyou.data.remote.datasource.ar

import com.ssafy.fundyou.data.remote.datasource.ar.dto.ArImageResponseDto

internal interface ArDataSource {
    suspend fun getArImageList(
        fundingId: Long,
        itemId: Long
    ): List<ArImageResponseDto>

    suspend fun saveArImage(request: ArImageSaveRequestDto): ArImageResponseDto

}