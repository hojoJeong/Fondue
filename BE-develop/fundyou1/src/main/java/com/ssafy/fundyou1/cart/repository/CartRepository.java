package com.ssafy.fundyou1.cart.repository;

import com.ssafy.fundyou1.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {



    List<Cart> findAllByMember_Id(Long memberId);

    Optional<Cart> findById(Long cartId);


    void deleteById(Long id);




    @Modifying
    @Query(value="DELETE FROM Cart c " +
            "WHERE c.member_id = :memberId " +
            "AND c.item_id = :id" ,nativeQuery = true)
    void deleteCartItem(@Param("memberId") Long memberId,@Param("id") Long id);


    @Modifying
    @Query(value="UPDATE Cart c " +
            "SET c.count = c.count + :count " +
            "WHERE c.item_id = :id " +
            "AND c.member_id = :memberId", nativeQuery = true)
    void updateAddCartItem( @Param("count") int count,@Param("id") Long id,@Param("memberId") Long memberId);



    @Query(value="Select * " +
            "From Cart c " +
            "WHERE c.item_id = :id " +
            "AND c.member_id = :memberId", nativeQuery = true)
    Cart findCartItem( @Param("id") Long id,@Param("memberId") Long memberId);

}