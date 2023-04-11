package com.ssafy.fundyou1.item.dto;


import com.ssafy.fundyou1.category.entity.Category;
import com.ssafy.fundyou1.item.entity.Description;
import com.ssafy.fundyou1.item.entity.Item;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemResponseDto {

    private Long itemId;

    private int price;

    private String image;

    private String title;

    private Boolean isAr;

    private String descriptionImg;

    private List<CustomDescriptionDto> description;

    private String brand;

    private Boolean isFavorite;

    private int sellingCount;

    private Category category;


    @Builder
    public ItemResponseDto(Item item,boolean b){
        this.itemId  = item.getId();
        this.price = item.getPrice();
        this.image = item.getImage();
        this.title = item.getTitle();
        this.isAr = item.getIsAr();
        this.descriptionImg = item.getDescriptionImg();
        this.description = changeDto(item);
        this.brand = item.getBrand();
        this.isFavorite = b;
        this.sellingCount = item.getSellingCount();
        this.category = item.getCategory();

    }

    // 커스텀 상세 목록 dto 데이터 변환 로직!
    public List<CustomDescriptionDto> changeDto(Item item){
        List<CustomDescriptionDto> desList = new ArrayList<>();
        for (Description des : item.getDescriptions()) {
            desList.add(new CustomDescriptionDto(des.getItemType(), des.getContent()));
        }
        return desList;
    }


}
