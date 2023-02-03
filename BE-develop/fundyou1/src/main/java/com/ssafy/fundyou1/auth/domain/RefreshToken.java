package com.ssafy.fundyou1.auth.domain;

import com.ssafy.fundyou1.global.exception.BusinessException;
import com.ssafy.fundyou1.global.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_id")
    private Long id;

    @Column(name = "refresh_token", length = 512)
    private String refreshToken;

    @Column(name = "subject", length = 32)
    private String subject;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    public RefreshToken() {
    }

    @Builder
    public RefreshToken(String refreshToken, String subject, LocalDateTime expiredAt) {
        this.refreshToken = refreshToken;
        this.subject = subject;
        this.expiredAt = expiredAt;
    }

    public void validateValue(String refreshToken) {
        if (!this.refreshToken.equals(refreshToken)) {
            throw new BusinessException(ErrorCode.INVALID_NOT_MATCH_BY_REFRESH_TOKEN);
        }
    }

    public void updateRefreshToken(String token) {
        this.refreshToken = token;
        this.expiredAt = LocalDateTime.now().plusDays(7);
    }
}
