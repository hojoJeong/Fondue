package com.ssafy.fundyou1.member.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.fundyou1.member.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class MemberSaveRequest {

    @ApiModelProperty(position = 2, notes = "비밀번호", example = "Zjsxlsb123!!")
    @JsonProperty("password")
    private String password;

    @ApiModelProperty(position = 3, notes = "유저네임아이디", example = "퐁듀")
    @JsonProperty("loginId")
    private String loginId;

    public MemberSaveRequest() {
    }

    public MemberSaveRequest(String loginId,String password) {
        this.password = password;
        this.loginId = loginId;
    }

    public Member toMember() {
        return Member.createMember(loginId,password);
    }
}