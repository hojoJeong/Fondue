package com.ssafy.fundyou1.auth.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class LoginRequest {

    @JsonProperty("loginId")
    @ApiModelProperty(required = true, position = 0, notes = "유저네임", example = "퐁듀")
    private String loginId;

    @JsonProperty("password")
    @ApiModelProperty(required = true, position = 1, notes = "비밀번호", example = "asdfq!!!!")
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
