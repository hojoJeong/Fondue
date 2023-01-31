package com.ssafy.fundyou.domain.usecase.search

import com.ssafy.fundyou.domain.repository.SearchRepository
import javax.inject.Inject

class DeleteAllHistoryKeywordUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke() = searchRepository.deleteAllHistoryKeyword()
}