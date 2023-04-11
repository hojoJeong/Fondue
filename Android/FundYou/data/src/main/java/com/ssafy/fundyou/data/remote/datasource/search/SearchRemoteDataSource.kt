package com.ssafy.fundyou.data.remote.datasource.search

import com.ssafy.fundyou.data.remote.datasource.search.dto.SearchPopularKeywordResponseDto

internal interface SearchRemoteDataSource {
    suspend fun getPopularKeywordList() : List<SearchPopularKeywordResponseDto>
}