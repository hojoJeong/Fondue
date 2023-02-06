package com.ssafy.fundyou.data.remote.service

import com.ssafy.fundyou.data.remote.datasource.favorite.dto.FavoriteResponseDto
import retrofit2.http.GET

interface FavoriteApiService {
    @GET("")
    suspend fun getFavoriteByUserId() : MutableList<FavoriteResponseDto>
}