package com.ssafy.fundyou1.cart.repository;

import com.ssafy.fundyou1.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartIdAndItem_ItemId(Long cartId, Long itemId);


    List<CartItem> findAllByCart_Id(Long cartId);


    void deleteById(Long cartItemId);



}