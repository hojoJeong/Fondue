package com.ssafy.fundyou1.cart.repository;

import com.ssafy.fundyou1.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findAllByMember_Id(Long memberId);


    // 장바구니에 아이템을 찾는다( 아이템 아이디, 회원아이디)
    @Query(value="Select * " +
            "From cart " +
            "WHERE item_id = :itemId " +
            "AND member_id = :memberId", nativeQuery = true)
    Cart findCartItem( @Param("itemId") Long itemId,@Param("memberId") Long memberId);


    //업데이트: 장바구니에서 동일 아이템 개수를 더한다.(개수, 아이템아이디, 멤버아이디)
    @Modifying
    @Query(value="UPDATE cart " +
            "SET count = count + :count " +
            "WHERE item_id = :itemId " +
            "AND member_id = :memberId", nativeQuery = true)
    void updateAddCartItem( @Param("count") int count,@Param("itemId") Long itemId,@Param("memberId") Long memberId);


    // 장바구니에서 (회원 아이디, 아이템 아이디)로 아이템을 삭제한다.
    @Modifying
    @Query(value="DELETE " +
            "FROM cart " +
            "WHERE member_id = :memberId " +
            "AND item_id = :itemId" ,nativeQuery = true)
    Integer deleteCartItem(@Param("memberId") Long memberId,@Param("itemId") Long itemId);

}