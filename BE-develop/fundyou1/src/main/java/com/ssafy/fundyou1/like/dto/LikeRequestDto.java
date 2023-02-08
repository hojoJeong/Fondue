package com.ssafy.fundyou1.like.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


//  찜에 담을 상품의 itemid를 전달받음
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikeRequestDto {

    @ApiModelProperty(position = 0, notes = "아이템 아이디", example = "1,2,3")
    @NotNull(message = "아이템 아이디")
    private Long itemId;

}