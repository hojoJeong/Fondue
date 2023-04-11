package com.ssafy.fundyou.domain.usecase.item

import com.ssafy.fundyou.domain.repository.ItemRepository
import javax.inject.Inject

class GetItemByPriceUseCase @Inject constructor(private val itemRepository: ItemRepository){
    suspend operator fun invoke(categoryId: Int, minPrice: Int, maxPrice: Int) = itemRepository.getItemByPrice(categoryId, minPrice, maxPrice)
}