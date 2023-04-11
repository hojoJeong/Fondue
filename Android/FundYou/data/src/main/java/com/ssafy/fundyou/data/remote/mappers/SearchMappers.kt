package com.ssafy.fundyou.data.remote.mappers

import com.ssafy.fundyou.data.remote.datasource.search.dto.SearchPopularKeywordResponseDto
import com.ssafy.fundyou.domain.model.search.PopularKeywordEntity

internal fun SearchPopularKeywordResponseDto.toDomainModel() = PopularKeywordEntity(
    id = this.id,
    keyword = this.keyword,
    searchCount = this.searchCount
)