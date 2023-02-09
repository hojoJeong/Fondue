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

    FundingItem findByFundingIdAndItemId(Long fundingId, Long itemId);

    @Query(value = "select funding_item_id from Funding_Item fi where fi.funding_Id = :fundingId", nativeQuery = true)
    List<Long> findIdListByFundingId(@Param("fundingId") Long fundingId);

    // 펀딩 상태 False로 변경
    @Modifying(clearAutomatically = true)
    @Query(value = "update Funding_Item fi set fi.funding_Item_Status = false where fi.funding_item_id = :id", nativeQuery = true)
    void changeFundingStatus(@Param("id") Long fundingItemId);

    // 현재 펀딩된 금액 변경(추가)
    @Modifying(clearAutomatically = true)
    @Query(value = "update Funding_Item fi set fi.current_Funding_Price = fi.current_Funding_Price + :point where fi.funding_item_id = :id", nativeQuery = true)
    void addCurrentFundingPrice(@Param("id") Long fundingItemId, @Param("point") int point);

    // 참여자 수 변경(추가)
    @Modifying(clearAutomatically = true)
    @Query(value = "update Funding_Item fi set fi.participants_Count =fi.participants_Count + 1 where fi.funding_item_id = :id", nativeQuery = true)
    void addParticipantsCount(@Param("id") Long fundingItemId);

    List<FundingItem> findByFundingId(Long fundingId);

    @Query(value = "select sum(item_Total_Price) from funding_Item fi where fi.funding_id = :fundingId", nativeQuery = true)
    int sumTotalPriceByFundingId(@Param("fundingId") Long fundingId);

    @Modifying(clearAutomatically = true)
    @Query(value = "select sum(current_funding_Price) from funding_Item fi where fi.funding_id = :fundingId", nativeQuery = true)
    int sumCurrentFundingPriceByFundingId(@Param("fundingId") Long fundingId);

    @Modifying(clearAutomatically = true)
    @Query(value = "update funding_item fi set fi.funding_item_status = :status where fi.funding_id = :fundingId", nativeQuery = true)
    void updateFundingItemStatusByFundingId(@Param("fundingId") Long fundingId, @Param("status") boolean status);

    @Modifying(clearAutomatically = true)
    @Query(value = "update funding_item fi set fi.funding_item_status = :status where fi.funding_item_id = :fundingItemId", nativeQuery = true)
    void updateFundingItemStatusByFundingItemId(@Param("fundingItemId") Long fundingItemId, @Param("status") boolean status);
}
