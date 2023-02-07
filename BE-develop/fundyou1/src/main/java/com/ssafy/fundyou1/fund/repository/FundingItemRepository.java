package com.ssafy.fundyou1.fund.repository;

import com.ssafy.fundyou1.fund.entity.FundingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundingItemRepository extends JpaRepository<FundingItem, Long> {

    FundingItem findByFundingIdAndItemId(Long fundingId, Long itemId);


//    @Query(value = "SELECT * FROM fundingItem fi WHERE fi.id = :fundingItemId")
//    FundingItem findByFundingItemId(Long fundingItemId);

    List<FundingItem> findByFundingId(Long fundingId);
}
