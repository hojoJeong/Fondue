package com.ssafy.fundyou1.item.dto;

import com.ssafy.fundyou1.item.entity.Item;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RandomItemResponse {
    @ApiModelProperty(name = "아이템 아이디", example = "1,2")
    private Long itemId;

    @ApiModelProperty(name = "아이템 타이틀", example = "쇼파")
    private String title;

    @ApiModelProperty(name = "아이템 가격", example = "10000")
    private int price;
    @ApiModelProperty(name = "아이템 이미지", example = "ssafy/img/thumbnail.jpg")
    private String image;
    @ApiModelProperty(name = "AR가능여부", example = "true / false")
    private Boolean isAr;
    @ApiModelProperty(name = "좋아요 여부", example = "true / false")
    private Boolean isFavorite;
    @ApiModelProperty(name = "브랜드", example = "한샘")
    private String brand;


    @Builder
    public RandomItemResponse(Item item, boolean b){
        this.itemId  = item.getId();
        this.title = item.getTitle();
        this.price = item.getPrice();
        this.image = item.getImage();
        this.isAr = item.getIsAr();
        this.isFavorite = b;
        this.brand = item.getBrand();
    }

}
