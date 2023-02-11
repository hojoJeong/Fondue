package com.ssafy.fundyou1.firebase;


import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class FcmMessageRequestDto {

    @ApiParam(value = "타겟 토큰", required = true)
    String targetToken;

    @ApiParam(value = "제목", required = true)
    String title;

    @ApiParam(value = "내용", required = true)
    String body;


}
