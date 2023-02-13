package com.ssafy.fundyou.data.remote.service

import com.ssafy.fundyou.data.remote.datasource.ar.dto.ArImageResponseDto
import com.ssafy.fundyou.data.remote.datasource.ar.dto.ArImageSaveRequestDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

internal interface ArApiService {

    @GET("/ar/img/list/{funding_id}/{item_id}")
    suspend fun getArImageList(
        @Path("funding_id") fundingId: Int,
        @Path("itemId") itemId: Int
    ): List<ArImageResponseDto>

    @POST("/ar/img/save")
    suspend fun saveArImage(
        @Body arImageSaveRequestDto: ArImageSaveRequestDto
    ): ArImageResponseDto
}