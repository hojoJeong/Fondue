package com.ssafy.fundyou.data.remote.datasource.ar

import com.ssafy.fundyou.data.remote.service.ArApiService
import javax.inject.Inject

internal class ArDataSourceImpl @Inject constructor(private val arApiService: ArApiService) :
    ArDataSource {


    override suspend fun saveArImage(request: ArImageSaveRequestDto) =
        arApiService.saveArImage(request)
}