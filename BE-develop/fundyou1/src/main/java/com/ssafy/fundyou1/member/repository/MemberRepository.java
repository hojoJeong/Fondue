package com.ssafy.fundyou1.member.repository;

import com.ssafy.fundyou1.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


}
