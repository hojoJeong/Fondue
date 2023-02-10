package com.ssafy.fundyou.domain.usecase.wishlist

import com.ssafy.fundyou.domain.repository.WishListRepository
import javax.inject.Inject

class DeleteWishListItemUseCase @Inject constructor(private val wishListRepository: WishListRepository) {
    suspend operator fun invoke(itemId: Int) = wishListRepository.deleteWishListItem(itemId)
}