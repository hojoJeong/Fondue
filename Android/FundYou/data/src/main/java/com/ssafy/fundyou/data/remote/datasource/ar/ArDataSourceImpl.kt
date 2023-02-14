package com.ssafy.fundyou.data.remote.datasource.ar

import com.ssafy.fundyou.data.remote.datasource.ar.dto.ArImageResponseDto
import com.ssafy.fundyou.data.remote.datasource.ar.dto.ArImageSaveRequestDto
import com.ssafy.fundyou.data.remote.service.ArApiService
import javax.inject.Inject

internal class ArDataSourceImpl @Inject constructor(private val arApiService: ArApiService) :
    ArDataSource {
    override suspend fun getArImageList(fundingItemId: Long): List<ArImageResponseDto> =
        arApiService.getArImageList(fundingItemId)


    override suspend fun saveArImage(fundingItemId: Long, url: String): ArImageResponseDto =
        arApiService.saveArImage(ArImageSaveRequestDto(fundingItemId, url))
}