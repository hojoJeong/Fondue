package com.ssafy.fundyou.domain.usecase.search

import com.ssafy.fundyou.domain.repository.SearchRepository
import javax.inject.Inject

class AddSearchHistoryKeyword @Inject constructor(
    private val searchRepository: SearchRepository
){
    suspend operator fun invoke(keyword : String) = searchRepository.addHistoryKeyword(keyword)
}