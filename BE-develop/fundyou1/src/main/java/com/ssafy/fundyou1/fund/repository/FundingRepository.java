package com.ssafy.fundyou1.fund.repository;

import com.ssafy.fundyou1.fund.entity.Funding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FundingRepository extends JpaRepository<Funding, Long> {

    Optional<Funding> findById(Long fundingId);

    Funding findByIdAndMemberId(Long fundingId, Long memberId);

}
