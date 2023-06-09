package com.ssafy.fundyou1.like.dto;


import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikeItemResponseDto {

    Long memberId;

    Long itemId;

    int price;

    String image;

    String title;

    Boolean isAr;

    private String brand;

    Boolean isFavorite;

    public LikeItemResponseDto(Member member, boolean b, Item item){
        this.memberId = member.getId();
        this.itemId  = item.getId();
        this.price = item.getPrice();
        this.image = item.getImage();
        this.title = item.getTitle();
        this.isAr = item.getIsAr();
        this.brand = item.getBrand();
        this.isFavorite = b;
    }

}
