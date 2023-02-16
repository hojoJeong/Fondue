package com.ssafy.fundyou1.fund.repository;

import com.ssafy.fundyou1.fund.dto.FundingResultMemberDto;
import com.ssafy.fundyou1.fund.entity.InvitedMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InvitedMemberRepository extends JpaRepository<InvitedMember, Long> {
    List<InvitedMember> findAllByMemberId(Long memberId);

    @Transactional
    @Query(value = "select case when count(*) = 1 then 'true' else 'false' end from invited_member where member_id = :memberId and funding_id = :fundingId", nativeQuery = true)
    Boolean checkExistByMemberIdAndFundingId(@Param("memberId") Long memberId, @Param("fundingId") Long fundingId);


    @Query(value = "select * from invited_member where funding_id = :fundingId", nativeQuery = true)
    List<InvitedMember> findAllByFundingId(@Param("fundingId") Long fundingId);
}
