package com.ssafy.fundyou.domain.usecase.item

import com.ssafy.fundyou.domain.repository.ItemRepository
import javax.inject.Inject

class GetRankingItemUseCase @Inject constructor(private val itemRepository: ItemRepository) {
    suspend operator fun invoke() = itemRepository.getRankingItem()
}