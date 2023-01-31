package com.ssafy.fundyou.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONArray

class SearchKeywordPreference(context: Context) {

    private val prefs = context.getSharedPreferences(KEY, Context.MODE_PRIVATE)

    /** 현재 저장된 검색어 가져오기 */
    fun getKeywordList(): List<String> {
        return decodeJSONArray(prefs.getString(KEY, null))
    }

    /** SharePreference에 데이터 저장 */
    fun addKeyword(value: String) {
        val baseList = getKeywordList()
        val jsonArray = JSONArray()

        jsonArray.put(value)
        baseList.forEach { keyword ->
            jsonArray.put(keyword)
        }

        prefs.edit().putString(KEY, jsonArray.toString()).apply()
    }


    fun removeKeyword(baseList: List<String>, index : Int) {
        val jsonArray = JSONArray()
        baseList.forEachIndexed { listIndex, keyword ->
            if(listIndex != index) jsonArray.put(keyword)
        }
        prefs.edit().putString(KEY, jsonArray.toString()).apply()
    }

    fun removeAllKeyword() {
        prefs.edit().remove(KEY).apply()
    }

    //String으로 받은 sp를 ArrayList로 변환
    private fun decodeJSONArray(json: String?): List<String> {
        val keywordList = mutableListOf<String>()

        if (json != null) {
            val jsonArray = JSONArray(json)
            for (idx in 0 until jsonArray.length()) {
                keywordList.add(jsonArray.optString(idx))
            }
        }
        return keywordList
    }

    companion object {
        private const val KEY = "SEARCH_KEYWORD"
    }
}