package com.ssafy.fundyou1.cart.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class CartDeleteReqestDto {

    @ApiModelProperty(name = "장바구니 item 아이디 ", example = "1")
    Long cartItemId;

}