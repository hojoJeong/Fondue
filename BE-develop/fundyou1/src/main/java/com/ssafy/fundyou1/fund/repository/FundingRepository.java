package com.ssafy.fundyou1.fund.repository;

import com.ssafy.fundyou1.fund.entity.Funding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FundingRepository extends JpaRepository<Funding, Long> {

    Optional<Funding> findById(Long fundingId);

    Funding findByIdAndMemberId(Long fundingId, Long memberId);

    @Query(value = "select * from Funding fi where fi.member_Id = :memberId and fi.funding_status = :status", nativeQuery = true)
    List<Funding> findAllByMemberIdAndByFundingStatus(@Param("memberId") Long memberId, @Param("status") boolean status);
}
