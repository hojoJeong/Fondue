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
    @ApiModelProperty(required = true, position = 0, notes = "유저네임", example = "퐁듀")
    private String loginId;

    @JsonProperty("password")
    @ApiModelProperty(required = true, position = 1, notes = "비밀번호", example = "asdfq!!!!")
    private String password;

}
