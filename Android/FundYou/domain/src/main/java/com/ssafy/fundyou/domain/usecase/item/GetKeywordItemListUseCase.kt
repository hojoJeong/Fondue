package com.ssafy.fundyou.domain.usecase.item

import com.ssafy.fundyou.domain.repository.ItemRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.min

@Singleton
class GetKeywordItemListUseCase @Inject constructor(
    private val itemRepository: ItemRepository
) {
    suspend operator fun invoke(keyword: String, minPrice : Int, maxPrice: Int) = itemRepository.getKeywordItemList(keyword, minPrice, maxPrice)
}