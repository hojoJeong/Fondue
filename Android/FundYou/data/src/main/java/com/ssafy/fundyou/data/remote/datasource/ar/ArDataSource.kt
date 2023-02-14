package com.ssafy.fundyou.data.remote.datasource.ar

import com.ssafy.fundyou.data.remote.datasource.ar.dto.ArImageResponseDto

internal interface ArDataSource {
    suspend fun getArImageList(
        fundingItemId: Long,
    ): List<ArImageResponseDto>

    suspend fun saveArImage(fundingItemId: Long, url: String): ArImageResponseDto

}