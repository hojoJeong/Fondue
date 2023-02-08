package com.ssafy.fundyou1.global.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

// 토큰 재발급 요청 DTO
@Getter
@NoArgsConstructor
public class TokenRequestDto {
    private String accessToken;
    private String refreshToken;
}
