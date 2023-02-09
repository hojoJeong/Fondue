package com.ssafy.fundyou1.fund.repository;

import com.ssafy.fundyou1.fund.entity.InvitedMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvitedMemberRepository extends JpaRepository<InvitedMember, Long> {
    List<InvitedMember> findAllByMemberId(Long memberId);
}
