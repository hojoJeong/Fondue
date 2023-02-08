package com.ssafy.fundyou1.fund.repository;

import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.fund.entity.FundingItemMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundingItemMemberRepository extends JpaRepository<FundingItemMember, Long> {
    List<FundingItemMember> findAllByMemberId(Long id);

    int findAllByMemberIdAndFundingItemId(Long id, Long fundingItemId);

    @Query(value = "select sum(funding_Item_Price) from Funding_Item_Member fim where fim.member_Id = :memberId and (fim.funding_Item_Id in :fundingItemIdList) ",nativeQuery = true)
    int findAllByMemberIdAndFundingItemList(@Param("memberId") Long memberId, @Param("fundingItemIdList") List<Long> fundingItemIdList);
}
