package com.ssafy.fundyou.data.remote.service

import com.ssafy.fundyou.data.remote.datasource.ar.dto.ArImageResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

internal interface ArApiService {

    @GET("/ar/img/list/{fundingItemId}")
    suspend fun getArImageList(
        @Path("fundingItemId") fundingItemId: Long,
    ): List<ArImageResponseDto>

    @GET("/ar/img/save/{fundingItemId}/{url}")
    suspend fun saveArImage(
        @Path("fundingItemId") fundingItemId: Long,
        @Path("url") url: String
    ): ArImageResponseDto
}