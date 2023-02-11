package com.ssafy.fundyou1.fund.repository;

import com.ssafy.fundyou1.fund.entity.FundingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundingItemRepository extends JpaRepository<FundingItem, Long> {


    @Query(value = "select funding_item_id from funding_item fi where fi.funding_id = :fundingId", nativeQuery = true)
    List<Long> findIdListByFundingId(@Param("fundingId") Long fundingId);

    // 펀딩 상태 False로 변경
    @Modifying(clearAutomatically = true)
    @Query(value = "update funding_item fi set fi.funding_item_status = false where fi.funding_item_id = :id", nativeQuery = true)
    void changeFundingStatus(@Param("id") Long fundingItemId);

    // 현재 펀딩된 금액 변경(추가)
    @Modifying(clearAutomatically = true)
    @Query(value = "update funding_item fi set fi.current_funding_price = fi.current_funding_price + :point where fi.funding_item_id = :id", nativeQuery = true)
    void addCurrentFundingPrice(@Param("id") Long fundingItemId, @Param("point") int point);

    // 참여자 수 변경(추가)
    @Modifying(clearAutomatically = true)
    @Query(value = "update funding_item fi set fi.participants_count =fi.participants_count + 1 where fi.funding_item_id = :id", nativeQuery = true)
    void addParticipantsCount(@Param("id") Long fundingItemId);

    List<FundingItem> findByFundingId(Long fundingId);

    @Query(value = "select sum(item_total_price) from funding_item fi where fi.funding_id = :fundingId", nativeQuery = true)
    Integer sumTotalPriceByFundingId(@Param("fundingId") Long fundingId);


    @Query(value = "select sum(current_funding_price) from funding_item fi where fi.funding_id = :fundingId", nativeQuery = true)
    Integer sumCurrentFundingPriceByFundingId(@Param("fundingId") Long fundingId);

    @Modifying(clearAutomatically = true)
    @Query(value = "update funding_item fi set fi.funding_item_status = :status where fi.funding_id = :fundingId", nativeQuery = true)
    void updateFundingItemStatusByFundingId(@Param("fundingId") Long fundingId, @Param("status") boolean status);

    @Modifying(clearAutomatically = true)
    @Query(value = "update funding_item fi set fi.funding_item_status = :status where fi.funding_item_id = :fundingItemId", nativeQuery = true)
    void updateFundingItemStatusByFundingItemId(@Param("fundingItemId") Long fundingItemId, @Param("status") boolean status);

    @Query(value = "select * from funding_item fi where fi.funding_item_id = :fundingItemId", nativeQuery = true)
    FundingItem findByFundingItemId(@Param("fundingItemId") Long fundingItemId);
}
