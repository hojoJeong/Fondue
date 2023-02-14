package com.ssafy.fundyou.data.remote.service

import com.ssafy.fundyou.data.remote.datasource.ar.dto.ArImageResponseDto
import com.ssafy.fundyou.data.remote.datasource.ar.dto.ArImageSaveRequestDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

internal interface ArApiService {

    @GET("/ar/img/list/{fundingItemId}")
    suspend fun getArImageList(
        @Path("fundingItemId") fundingItemId: Long,
    ): List<ArImageResponseDto>

    @POST("/ar/img/save")
    suspend fun saveArImage(
        @Body arImageSaveRequestDto: ArImageSaveRequestDto
    ): ArImageResponseDto
}