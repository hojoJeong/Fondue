package com.ssafy.fundyou1.cart.repository;

import com.ssafy.fundyou1.cart.dto.CartDetailDto;
import com.ssafy.fundyou1.cart.entity.CartItem;
import com.ssafy.fundyou1.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartIdAndItemId(Long cartId, Long itemId);


    // 장바구니 페이지에 전달할 CartDetailDto 를 쿼리로 조회해서 CartDetailDtoList 에 담아줌

//    @Query(value =
//            "SELECT * " +
//            "FROM cart_item " +
//            "JOIN item " +
//            "ON cart_item.item_id = item.id " +
//            "WHERE cart_item.cart_id = :cartId" ,
//            nativeQuery = true)

    @Query(value =
            "SELECT * FROM item WHERE item.id in (SELECT cart_item.item_id FROM cart_item ci WHERE ci.cart_id = :cartId)",
            nativeQuery = true)
    List<Item> findCartDetailDtoList(@Param("cartId") Long cartId);

}