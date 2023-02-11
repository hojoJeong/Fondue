package com.ssafy.fundyou1.cart.dto;

import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.member.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponseDto {


    Long memberId;
    Long itemId;
    int price;
    String image;
    String title;

    String brand;
    Boolean isAr;

    Boolean isFavorite;

    int count;


    public CartItemResponseDto(Item item, Member member, int count){
        this.memberId = member.getId();
        this.brand = item.getBrand();
        this.itemId  = item.getId();
        this.price = item.getPrice();
        this.image = item.getImage();
        this.title = item.getTitle();
        this.isAr = item.getIsAr();
        this.isFavorite = item.getIsFavorite();
        this.count = count;
    }

}