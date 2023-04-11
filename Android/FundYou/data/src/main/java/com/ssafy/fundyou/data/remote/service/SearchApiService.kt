package com.ssafy.fundyou.data.remote.service

import com.ssafy.fundyou.data.remote.datasource.search.dto.SearchPopularKeywordResponseDto
import retrofit2.http.GET

internal interface SearchApiService {
    @GET("/search/keyword/rank")
    suspend fun getPopularSearchKeyword() : List<SearchPopularKeywordResponseDto>
}