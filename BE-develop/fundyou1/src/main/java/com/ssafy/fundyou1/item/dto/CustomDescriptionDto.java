package com.ssafy.fundyou1.item.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;

// 커스텀 아이템 상세 목록 DTO
@NoArgsConstructor
@Getter
@ToString
public class CustomDescriptionDto {


    private String itemType;

    private String content;


    @Builder
    public CustomDescriptionDto(String itemType, String content) {
        this.itemType = itemType;
        this.content = content;
    }



}
