package com.ssafy.fundyou.data.remote.datasource.search

import com.ssafy.fundyou.data.remote.datasource.search.dto.SearchPopularKeywordResponseDto

interface SearchRemoteDataSource {
    suspend fun getPopularKeywordList() : List<SearchPopularKeywordResponseDto>
}