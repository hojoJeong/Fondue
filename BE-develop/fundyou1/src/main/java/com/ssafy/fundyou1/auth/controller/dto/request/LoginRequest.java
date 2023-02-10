package com.ssafy.fundyou1.auth.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 일반 로그인 할때 받아오는 DTO
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @JsonProperty("loginId")
    private String loginId;

    @JsonProperty("password")
    private String password;

}
