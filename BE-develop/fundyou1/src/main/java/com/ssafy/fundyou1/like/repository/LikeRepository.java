package com.ssafy.fundyou1.like.repository;

import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {


    // 사용자가 등록한 like 목록 다 가져오기 ,
    List<Like> findAllByMember_Id(Long memberId);


    // 찜 목록 아이템 삭제

    @Modifying
    @Query(value="DELETE FROM Likes li " +
            "WHERE li.member_id = :memberId " +
            "AND li.item_id = :id" ,nativeQuery = true)
    void deleteLikeItem(@Param("memberId") Long memberId, @Param("id") Long id);

    // 찜 아이템 찾기 ( 아이템 아이디, 회원 아이디)

    @Query(value="Select * " +
            "From Likes li " +
            "WHERE li.item_id = :id " +
            "AND li.member_id = :memberId", nativeQuery = true)
    Like findLikeItem(@Param("id") Long id, @Param("memberId") Long memberId);

    // Item의 is_favorite 값 바꾸기
    @Modifying
    @Query(value="UPDATE Item i " +
            "SET i.is_favorite = :b " +
            "WHERE i.item_id = :id"
            , nativeQuery = true)
    void updateItemIsFavorite( @Param("id") Long id, @Param("b") boolean b);




}
