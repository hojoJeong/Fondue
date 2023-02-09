package com.ssafy.fundyou1.item.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;

// 커스텀 아이템 상세 목록 DTO
@NoArgsConstructor
@Getter
@ToString
public class CustomDescriptionDto {


    @ApiModelProperty(position = 0, notes = "속성", example = "색상")
    private String type;

    @ApiModelProperty(position = 1, notes = "값", example = "red")
    private String value;


    @Builder
    public CustomDescriptionDto(String type, String value) {
        this.type = type;
        this.value = value;
    }



}
