package com.ssafy.fundyou1.member.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.fundyou1.member.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class MemberDeleteRequest {
    private String loginId;

}
