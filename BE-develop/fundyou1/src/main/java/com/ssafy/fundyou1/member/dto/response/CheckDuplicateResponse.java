package com.ssafy.fundyou1.member.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class CheckDuplicateResponse {

    @JsonProperty("result")
    @ApiModelProperty(notes = "중복 검사 결과", example = "true")
    private Boolean result;

    public CheckDuplicateResponse(Boolean result) {
        this.result = result;
    }


}
