package com.ssafy.fundyou1.member.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class ChangeTokenResponse {

    @JsonProperty("change_token")
    @ApiModelProperty(position = 1, notes = "비밀번호 변경 토큰", example = "hcT2TGS64fd7")
    private String changeToken;

    public ChangeTokenResponse(String changeToken) {
        this.changeToken = changeToken;
    }

    public static ChangeTokenResponse from (String changeToken){
        return new ChangeTokenResponse(changeToken);
    }
}
