package com.ssafy.fundyou1.member.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.fundyou1.member.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class MemberDeleteRequest {
    @ApiModelProperty(position = 3, notes = "유저네임아이디", example = "퐁듀")
    @JsonProperty("loginId")
    private String loginId;

    public MemberDeleteRequest() {
    }

    public MemberDeleteRequest(String loginId) {
        this.loginId = loginId;
    }



}
