package com.ssafy.fundyou1.cart.repository;

import com.ssafy.fundyou1.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartIdAndItemId(Long cartId, Long itemId);

    List<CartItem> findAllByCartId(Long cartId);
}