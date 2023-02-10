package com.ssafy.fundyou.domain.usecase.wishlist

import com.ssafy.fundyou.domain.repository.WishListRepository
import javax.inject.Inject

class AddWishListItemUseCase @Inject constructor(private val wishListRepository: WishListRepository) {
    suspend operator fun invoke(count: Int, itemId: Int) = wishListRepository.addWishListItem(count, itemId)
}