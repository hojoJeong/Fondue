package com.ssafy.fundyou.domain.usecase.item

import com.ssafy.fundyou.domain.repository.ItemRepository
import javax.inject.Inject

class AddLikeItemUseCase @Inject constructor(private val itemRepository: ItemRepository) {
    suspend operator fun invoke(itemId: Long) = itemRepository.addLikeItem(itemId)
}