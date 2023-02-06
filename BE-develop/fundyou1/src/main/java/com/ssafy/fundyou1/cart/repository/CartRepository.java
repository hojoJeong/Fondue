package com.ssafy.fundyou1.cart.repository;

import com.ssafy.fundyou1.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByMemberId(Long memberId);
}