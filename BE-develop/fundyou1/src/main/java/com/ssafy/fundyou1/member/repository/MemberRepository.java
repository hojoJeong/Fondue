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
    @Query(value = "update Member m set m.point = m.point - :point where m.username = :username", nativeQuery = true)
    void minusPoint(@Param("username") String username, @Param("point") int point);
}
