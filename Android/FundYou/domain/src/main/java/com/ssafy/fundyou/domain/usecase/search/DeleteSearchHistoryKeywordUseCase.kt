package com.ssafy.fundyou.domain.usecase.search

import com.ssafy.fundyou.domain.repository.SearchRepository
import javax.inject.Inject

class DeleteSearchHistoryKeywordUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(keywordList : ArrayList<String>, index : Int) = searchRepository.deleteHistoryKeyword(keywordList, index)
}