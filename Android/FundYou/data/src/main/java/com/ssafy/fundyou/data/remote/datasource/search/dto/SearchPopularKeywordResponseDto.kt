package com.ssafy.fundyou.data.remote.datasource.search.dto

import com.google.gson.annotations.SerializedName

data class SearchPopularKeywordResponseDto(
    @SerializedName("id")
    val id : Int,
    @SerializedName("keyword")
    val keyword : String,
    @SerializedName("search_count")
    val searchCount : Int
)