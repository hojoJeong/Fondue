package com.ssafy.fundyou1.auth.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findBySubject(String subject);

    int deleteByExpiredAtBefore(LocalDateTime time);
}
