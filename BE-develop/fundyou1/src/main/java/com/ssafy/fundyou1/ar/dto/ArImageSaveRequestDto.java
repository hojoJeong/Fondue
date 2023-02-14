package com.ssafy.fundyou1.ar.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArImageSaveRequestDto {
    private Long fundingItemId;
    private String url;
}
