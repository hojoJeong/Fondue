package com.ssafy.fundyou1.like.repository;

import com.ssafy.fundyou1.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {

    List<Like> findAllByMember_Id(Long memberId);

    boolean existsByItem_Id(Long itemId);


    @Modifying
    @Query(value="DELETE FROM Likes li " +
            "WHERE li.member_id = :memberId " +
            "AND li.item_id = :id" ,nativeQuery = true)
    void deleteLikeItem(@Param("memberId") Long memberId, @Param("id") Long id);


    @Query(value="Select * " +
            "From Likes li " +
            "WHERE li.item_id = :id " +
            "AND li.member_id = :memberId", nativeQuery = true)
    Like findLikeItem(@Param("id") Long id, @Param("memberId") Long memberId);


}
