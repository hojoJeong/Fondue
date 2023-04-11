package com.ssafy.fundyou.domain.repository

import com.ssafy.fundyou.domain.model.search.PopularKeywordEntity

interface SearchRepository {
    suspend fun getHistoryKeyword() : List<String>
    suspend fun deleteHistoryKeyword(baseList: List<String>, index: Int) : Int
    suspend fun deleteAllHistoryKeyword() : Int
    suspend fun addHistoryKeyword(keyword : String) : Int

    suspend fun getPopularKeywordList() : List<PopularKeywordEntity>
}