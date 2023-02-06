package com.ssafy.fundyou1.cart.dto;

import com.ssafy.fundyou1.global.dto.ResponseDto;
import lombok.Builder;

public class CartItemAddResponseDto extends ResponseDto {

    @Builder
    CartItemAddResponseDto(Boolean success, String message){
        super(success, message);
    }
}

