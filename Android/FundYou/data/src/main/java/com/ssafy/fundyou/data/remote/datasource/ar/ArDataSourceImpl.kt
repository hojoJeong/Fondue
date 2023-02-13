package com.ssafy.fundyou.data.remote.datasource.ar

import com.ssafy.fundyou.data.remote.datasource.ar.dto.ArImageResponseDto
import com.ssafy.fundyou.data.remote.datasource.ar.dto.ArImageSaveRequestDto
import com.ssafy.fundyou.data.remote.service.ArApiService
import javax.inject.Inject

internal class ArDataSourceImpl @Inject constructor(private val arApiService: ArApiService) :
    ArDataSource {
    override suspend fun getArImageList(fundingId: Int, itemId: Int): List<ArImageResponseDto> =
        arApiService.getArImageList(fundingId, itemId)


    override suspend fun saveArImage(request: ArImageSaveRequestDto) =
        arApiService.saveArImage(request)
}