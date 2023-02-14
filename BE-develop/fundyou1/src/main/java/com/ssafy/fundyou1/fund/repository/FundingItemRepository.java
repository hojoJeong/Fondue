package com.ssafy.fundyou1.fund.repository;

import com.ssafy.fundyou1.fund.entity.FundingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FundingItemRepository extends JpaRepository<FundingItem, Long> {


    @Query(value = "select funding_item_id from funding_item where funding_id = :fundingId", nativeQuery = true)
    List<Long> findIdListByFundingId(@Param("fundingId") Long fundingId);

    // 펀딩 상태 False로 변경
    @Modifying(clearAutomatically = true)
    @Query(value = "update funding_item set funding_item_status = false where funding_item_id = :id", nativeQuery = true)
    void changeFundingStatus(@Param("id") Long fundingItemId);

    // 현재 펀딩된 금액 변경(추가)
    @Modifying(clearAutomatically = true)
    @Query(value = "update funding_item set current_funding_price = current_funding_price + :point where funding_item_id = :id", nativeQuery = true)
    void addCurrentFundingPrice(@Param("id") Long fundingItemId, @Param("point") int point);

    // 참여자 수 변경(추가)
    @Modifying(clearAutomatically = true)
    @Query(value = "update funding_item fi set participants_count = participants_count + 1 where funding_item_id = :id", nativeQuery = true)
    void addParticipantsCount(@Param("id") Long fundingItemId);

    List<FundingItem> findByFundingId(Long fundingId);

    @Query(value = "select nvl(sum(item_total_price), 0) from funding_item where funding_id = :fundingId", nativeQuery = true)
    Integer sumTotalPriceByFundingId(@Param("fundingId") Long fundingId);


    @Query(value = "select NVL(sum(current_funding_price), 0) from funding_item where funding_id = :fundingId", nativeQuery = true)
    Integer sumCurrentFundingPriceByFundingId(@Param("fundingId") Long fundingId);

    @Modifying(clearAutomatically = true)
    @Query(value = "update funding_item set funding_item_status = :status where funding_id = :fundingId", nativeQuery = true)
    void updateFundingItemStatusByFundingId(@Param("fundingId") Long fundingId, @Param("status") boolean status);

    @Modifying(clearAutomatically = true)
    @Query(value = "update funding_item fi set funding_item_status = :status where funding_item_id = :fundingItemId", nativeQuery = true)
    void updateFundingItemStatusByFundingItemId(@Param("fundingItemId") Long fundingItemId, @Param("status") boolean status);

    @Query(value = "select * from funding_item fi where funding_item_id = :fundingItemId", nativeQuery = true)
    FundingItem findByFundingItemId(@Param("fundingItemId") Long fundingItemId);

    @Query(value = "select case when count(*) = 1 then 'true' else 'false' end from funding_item where item_id = :itemId and funding_id = :fundingId", nativeQuery = true)
    boolean findByFundingIdAndItemId(@Param("fundingId") Long fundingId, @Param("itemId") Long itemId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update funding_item set count = count + :count, item_total_price = item_total_price + (:itemPrice * :count) where item_id = :itemId and funding_id = :fundingId", nativeQuery = true)
    void addFundingItem(@Param("fundingId") Long fundingId, @Param("itemId") Long itemId, @Param("count") int count, @Param("itemPrice") int itemPrice);

    @Query(value = "select case when count(*) = 1 then 'true' else 'false' end from funding_item where funding_id = :fundingId and funding_item_status = :status", nativeQuery = true)
    boolean findByFundingIdAndFundingItemStatus(@Param("fundingId") Long fundingId, @Param("status") boolean status);
}
