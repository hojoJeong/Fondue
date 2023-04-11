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
    private Long itemId;

    private String title;

    private int price;

    private String image;

    private Boolean isAr;

    private Boolean isFavorite;

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
