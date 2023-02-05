package com.ssafy.fundyou1.fund.repository;

import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FundingRepository extends JpaRepository<Funding, Long> {

    Funding findByFundingId(Long fundingId);

}
