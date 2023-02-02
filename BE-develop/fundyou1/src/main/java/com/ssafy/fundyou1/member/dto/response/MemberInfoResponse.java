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


    @JsonProperty("username")
    @ApiModelProperty(position = 2, notes = "유저네임", example = "퐁듀")
    private String username;


    public MemberInfoResponse(Long id,String username) {
        this.id = id;
        this.username = username;
    }

    public static MemberInfoResponse from(Member member) {
        return new MemberInfoResponse(
            member.getId(),
            member.getLoginId()
        );
    }
}
