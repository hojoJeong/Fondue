package com.ssafy.fundyou1.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailDto {

    private Long cartItemId; //장바구니 상품 아이디

    private String title; //상품명

    private int price; //상품 금액

    private int count; //수량

    private String image; //상품 이미지 경로

//    public CartDetailDto(Long cartItemId, String title, int price, int count, String image){ // 장바구니에 전달할 애들인데, 그냥 어노테이션 씀
//        this.cartItemId = cartItemId;
//        this.title = title;
//        this.price = price;
//        this.count = count;
//        this.image = image;
//    }
}
