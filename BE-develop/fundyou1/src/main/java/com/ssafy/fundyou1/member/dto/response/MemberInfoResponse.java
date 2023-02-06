package com.ssafy.fundyou1.member.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.fundyou1.member.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class MemberInfoResponse {

    @JsonProperty("id")
    @ApiModelProperty(position = 1, notes = "ID", example = "1")
    private Long id;


    @JsonProperty("loginId")
    @ApiModelProperty(position = 2, notes = "유저아이디", example = "퐁듀")
    private String loginId;


    public MemberInfoResponse(Long id,String loginId) {
        this.id = id;
        this.loginId = loginId;
    }

    public static MemberInfoResponse from(Member member) {
        return new MemberInfoResponse(
            member.getId(),
            member.getLoginId()
        );
    }
}
