package com.ssafy.fundyou.domain.usecase.search

import com.ssafy.fundyou.domain.repository.SearchRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPopularKeywordListUseCase @Inject constructor(private val searchRepository: SearchRepository) {
    suspend operator fun invoke() = searchRepository.getPopularKeywordList()
}