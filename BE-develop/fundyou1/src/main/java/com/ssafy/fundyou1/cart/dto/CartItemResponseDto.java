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

    @ApiModelProperty(name = "장바구니 회원 아이디 ", example = "1,2")
    Long memberId;
    @ApiModelProperty(name = "아이템 아이디", example = "1,2")
    Long itemId;
    @ApiModelProperty(name = "아이템 가격", example = "10000")
    int price;
    @ApiModelProperty(name = "아이템 이미지", example = "ssafy/img/thumbnail.jpg")
    String image;
    @ApiModelProperty(name = "아이템 타이틀", example = "쇼파")
    String title;
    @ApiModelProperty(name = "AR가능여부", example = "N / Y")
    Boolean isAr;

    @ApiModelProperty(name = "AR가능여부", example = "N / Y")
    Boolean isFavorite;

    @ApiModelProperty(name = "장바구니 아이템 개수", example = "1,2,3")
    int count;


    public CartItemResponseDto(Item item, Member member, int count){
        this.memberId = member.getId();
        this.itemId  = item.getId();
        this.price = item.getPrice();
        this.image = item.getImage();
        this.title = item.getTitle();
        this.isAr = item.getIsAr();
        this.isFavorite = item.getIsFavorite();
        this.count = count;
    }

}