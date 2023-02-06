package com.ssafy.fundyou1.cart.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
public class CartItemAddRequestDto {

    private Long id;

    private int cartItemCount;

    @Builder
    public CartItemAddRequestDto(Long id, int cartItemCount) {
        this.id = id;
        this.cartItemCount = cartItemCount;
    }
}
