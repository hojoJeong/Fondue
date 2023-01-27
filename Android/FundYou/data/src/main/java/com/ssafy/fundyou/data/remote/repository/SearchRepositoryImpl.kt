package com.ssafy.fundyou.data.remote.repository

import android.os.Build.VERSION_CODES.S
import com.ssafy.fundyou.common.Constant
import com.ssafy.fundyou.common.Constant.SP_ERROR
import com.ssafy.fundyou.common.Constant.SUCCESS
import com.ssafy.fundyou.data.local.prefs.SearchKeywordPreference
import com.ssafy.fundyou.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchKeywordPreference: SearchKeywordPreference
) : SearchRepository {
    override suspend fun getHistoryKeyword(): ArrayList<String> {
        return searchKeywordPreference.getKeywordList()
    }

    override suspend fun deleteHistoryKeyword(baseList: ArrayList<String>, index: Int): Int {
        val baseSize = baseList.size
        searchKeywordPreference.removeKeyword(baseList, index)
        return if (searchKeywordPreference.getKeywordList().size == baseSize) SP_ERROR else SUCCESS
    }

    override suspend fun deleteAllHistoryKeyword(): Int {
        searchKeywordPreference.removeAllKeyword()
        return if (getHistoryKeyword().isEmpty()) SP_ERROR else SUCCESS
    }
}