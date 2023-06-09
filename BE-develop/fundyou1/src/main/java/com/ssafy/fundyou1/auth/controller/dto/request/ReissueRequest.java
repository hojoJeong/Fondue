package com.ssafy.fundyou1.auth.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 토큰 재발급시
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReissueRequest {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

}
