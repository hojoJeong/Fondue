package com.ssafy.fundyou1.member.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class MemberChangeRequest {

    @ApiModelProperty(position = 1, notes = "아이디 유저네임", example = "퐁듀")
    @JsonProperty("loginId")
    private String loginId;

    public MemberChangeRequest() {
    }

    public MemberChangeRequest(String loginId) {
        this.loginId = loginId;
    }
}
