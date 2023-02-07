package com.ssafy.fundyou.data.remote.datasource.search

import com.ssafy.fundyou.data.remote.datasource.search.dto.SearchPopularKeywordResponseDto
import com.ssafy.fundyou.data.remote.service.SearchApiService
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(
    private val searchApiService: SearchApiService
) : SearchRemoteDataSource{
    override suspend fun getPopularKeywordList(): List<SearchPopularKeywordResponseDto> {
        return searchApiService.getPopularSearchKeyword()
    }
}