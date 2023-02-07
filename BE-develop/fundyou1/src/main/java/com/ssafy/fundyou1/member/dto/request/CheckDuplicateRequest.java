package com.ssafy.fundyou1.member.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class CheckDuplicateRequest {

    @ApiModelProperty(name = "값")
    @JsonProperty("value")
    private String value;
}
