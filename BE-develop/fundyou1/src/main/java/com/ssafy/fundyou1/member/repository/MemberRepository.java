package com.ssafy.fundyou1.member.repository;

import com.ssafy.fundyou1.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginId(String loginId);

    boolean existsByLoginId(String loginId);



    Optional<Member> findByLoginIdAndDeletedAtNull(String loginId);

}
