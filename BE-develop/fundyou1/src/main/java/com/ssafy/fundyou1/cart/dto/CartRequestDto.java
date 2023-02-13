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

    private Long itemId;

    private int count;

}