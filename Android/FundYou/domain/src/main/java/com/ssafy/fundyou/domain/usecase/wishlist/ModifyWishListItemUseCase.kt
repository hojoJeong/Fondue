package com.ssafy.fundyou.domain.usecase.wishlist

import com.ssafy.fundyou.domain.repository.WishListRepository
import javax.inject.Inject

class ModifyWishListItemUseCase @Inject constructor(private val wishListRepository: WishListRepository) {
    suspend operator fun invoke(count: Int, itemId: Int) = wishListRepository.modifyWishListItem(count, itemId)
}