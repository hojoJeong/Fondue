package com.ssafy.fundyou1.global.dto;

import lombok.*;

// 토큰 발급 DTO
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;
}