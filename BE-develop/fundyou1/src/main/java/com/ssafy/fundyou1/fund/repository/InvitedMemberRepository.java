package com.ssafy.fundyou1.fund.repository;

import com.ssafy.fundyou1.fund.entity.InvitedMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitedMemberRepository extends JpaRepository<InvitedMember, Long> {
}
