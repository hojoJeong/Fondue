package com.ssafy.fundyou1.fund.repository;

import com.ssafy.fundyou1.fund.entity.FundingItemMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundingItemMemberRepository extends JpaRepository<FundingItemMember, Long> {
    List<FundingItemMember> findAllByMemberId(Long id);
}
