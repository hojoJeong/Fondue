package com.ssafy.fundyou1.member.repository;

import com.ssafy.fundyou1.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginId(String loginId);

    boolean existsByLoginId(String loginId);

    Member findByUsername(String username);

    // point 차감
    @Modifying(clearAutomatically = true)
    @Query(value = "update Member m set m.point = m.point - :point where m.member_id = :memberId", nativeQuery = true)
    void minusPoint(@Param("memberId") Long memberId, @Param("point") int point);

    @Modifying
    @Query(value = "update member set point = point + :point where member_id = :member_id",nativeQuery = true)
    Integer chargePoint(@Param("point") Long point, @Param("member_id") Long member_id);
}
