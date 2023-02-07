package com.ssafy.fundyou.data.remote.repository

import android.os.Build.VERSION_CODES.S
import com.ssafy.fundyou.common.Constant
import com.ssafy.fundyou.common.Constant.SP_ERROR
import com.ssafy.fundyou.common.Constant.SUCCESS
import com.ssafy.fundyou.data.local.prefs.SearchKeywordPreference
import com.ssafy.fundyou.data.remote.datasource.search.SearchRemoteDataSource
import com.ssafy.fundyou.data.remote.mappers.toDomainModel
import com.ssafy.fundyou.domain.model.search.PopularKeywordEntity
import com.ssafy.fundyou.domain.repository.SearchRepository
import javax.inject.Inject

internal class SearchRepositoryImpl @Inject constructor(
    private val searchKeywordPreference: SearchKeywordPreference,
    private val searchRemoteDataSource: SearchRemoteDataSource
) : SearchRepository {
    override suspend fun getHistoryKeyword(): List<String> {
        val response = searchKeywordPreference.getKeywordList()
        val subListSize = if (response.size > 5) 5
        else response.size

        return response.subList(0, subListSize)
    }

    override suspend fun deleteHistoryKeyword(baseList: List<String>, index: Int): Int {
        val baseSize = baseList.size
        searchKeywordPreference.removeKeyword(baseList, index)
        return if (getHistoryKeyword().size != baseSize) SUCCESS else SP_ERROR
    }

    override suspend fun deleteAllHistoryKeyword(): Int {
        searchKeywordPreference.removeAllKeyword()
        return if (getHistoryKeyword().isEmpty()) SUCCESS else SP_ERROR
    }

    override suspend fun addHistoryKeyword(keyword: String): Int {
        searchKeywordPreference.addKeyword(keyword)
        val checkList = getHistoryKeyword()
        return if (checkList[0] == keyword) SUCCESS else SP_ERROR
    }

    override suspend fun getPopularKeywordList(): List<PopularKeywordEntity> {
        return searchRemoteDataSource.getPopularKeywordList().map { it.toDomainModel() }
    }
}