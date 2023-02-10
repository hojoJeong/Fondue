package com.ssafy.fundyou1.firebase;


import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class FcmCreateRequestDto {


    @ApiParam(value = "타겟 토큰", required = true)
    Long memberId;


    @ApiParam(value = "디바이스 토큰", required = true)
    String targetToken;


}
