package com.ssafy.fundyou.data.remote.repository

import com.ssafy.fundyou.data.local.prefs.SearchKeywordPreference
import com.ssafy.fundyou.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchKeywordPreference: SearchKeywordPreference
) : SearchRepository {
    override suspend fun getHistoryKeyword(): List<String> {
        return searchKeywordPreference.getKeywordList()
    }

    override suspend fun deleteHistoryKeyword(baseList: ArrayList<String>, index: Int): Int {
        val baseSize = baseList.size
        searchKeywordPreference.removeKeyword(baseList, index)
        return if (searchKeywordPreference.getKeywordList().size == baseSize) 0 else 1
    }

    override suspend fun deleteAllHistoryKeyword(): Int {
        searchKeywordPreference.removeAllKeyword()
        return if (getHistoryKeyword().isEmpty()) 0 else 1
    }
}