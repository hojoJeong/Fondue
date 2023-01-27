package com.ssafy.fundyou.domain.repository

interface SearchRepository {
    suspend fun getHistoryKeyword() : List<String>
    suspend fun deleteHistoryKeyword(baseList: ArrayList<String>, index: Int) : Int
    suspend fun deleteAllHistoryKeyword() : Int
}