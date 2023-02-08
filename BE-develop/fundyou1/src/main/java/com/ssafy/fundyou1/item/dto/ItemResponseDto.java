package com.ssafy.fundyou1.item.dto;


import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.member.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDto {

    @ApiModelProperty(name = "아이템 아이디", example = "1,2")
    Long itemId;

    @ApiModelProperty(name = "아이템 가격", example = "10000")
    int price;

    @ApiModelProperty(name = "아이템 이미지", example = "ssafy/img/thumbnail.jpg")
    String image;

    @ApiModelProperty(name = "아이템 타이틀", example = "쇼파")
    String title;

    @ApiModelProperty(name = "AR가능여부", example = "true / false")
    Boolean isAr;

    @Column(name = "description")
    @ElementCollection
    private List<String> description;

    @Column(name = "brand")
    private String brand;

    @ApiModelProperty(name = "좋아요 여부 ", example = "true / false")
    Boolean isFavorite;



    public ItemResponseDto(Item item,boolean b){
        this.itemId  = item.getId();
        this.price = item.getPrice();
        this.image = item.getImage();
        this.title = item.getTitle();
        this.isAr = item.getIsAr();
        this.description = item.getDescription();
        this.brand = item.getBrand();
        this.isFavorite = b;

    }

}
