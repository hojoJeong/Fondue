package com.ssafy.fundyou1.cart.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
// 장바구니에 담을 상품의 id 와 수량 전달 받음
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartRequestDto {

    @ApiModelProperty(name = "아이템 아이디 ", example = "1,2")
    @NotNull(message = "아이템 아이디를 넣어주세요")
    private Long itemId;

    @ApiModelProperty(name = "장바구니에 담을 아이템 개수 ", example = "1,2")
    @Min(value = 1, message = "최소 1개 이상 담아주세요")
    private int count;

}