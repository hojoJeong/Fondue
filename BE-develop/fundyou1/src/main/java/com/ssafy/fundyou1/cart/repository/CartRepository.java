package com.ssafy.fundyou1.cart.repository;

import com.ssafy.fundyou1.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


    List<Cart> findAllByMember_Id(Long memberId);


    // 장바구니에 아이템을 찾는다( 아이템 아이디, 회원아이디)
    @Query(value="Select * " +
            "From cart c " +
            "WHERE c.item_id = :id " +
            "AND c.member_id = :memberId", nativeQuery = true)
    Cart findCartItem( @Param("id") Long id,@Param("memberId") Long memberId);


    // 장바구니에서 아이템 개수를 추가 업데이트 한다.(숫자, 아이템아이디, 멤버아이디)
    @Modifying
    @Query(value="UPDATE cart c " +
            "SET c.count = c.count + :count " +
            "WHERE c.item_id = :id " +
            "AND c.member_id = :memberId", nativeQuery = true)
    void updateAddCartItem( @Param("count") int count,@Param("id") Long id,@Param("memberId") Long memberId);


    // 장바구니에서 (회원 아이디, 아이템 아이디)로 물건을 삭제한다.
    @Modifying
    @Query(value="DELETE " +
            "FROM cart " +
            "WHERE member_id = :memberId " +
            "AND item_id = :itemId" ,nativeQuery = true)
    Integer deleteCartItem(@Param("memberId") Long memberId,@Param("itemId") Long itemId);

}