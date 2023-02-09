package com.ssafy.fundyou1.item.dto;


import com.ssafy.fundyou1.category.entity.Category;
import com.ssafy.fundyou1.item.entity.Description;
import com.ssafy.fundyou1.item.entity.Item;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDto {

    @ApiModelProperty(name = "아이템 아이디", example = "1,2")
    private Long itemId;

    @ApiModelProperty(name = "아이템 가격", example = "10000")
    private int price;

    @ApiModelProperty(name = "아이템 이미지", example = "ssafy/img/thumbnail.jpg")
    private String image;

    @ApiModelProperty(name = "아이템 타이틀", example = "쇼파")
    private String title;

    @ApiModelProperty(name = "AR가능여부", example = "true / false")
    private Boolean isAr;

    @ApiModelProperty(name = "상품 상세 이미지", example = "ssafy/img/item.jpg")
    private String descriptionImg;

    @ApiModelProperty(name = "상세 정보", example = "type, value")
    private List<CustomDescriptionDto> description;

    @ApiModelProperty(name = "브랜드", example = "한샘")
    private String brand;

    @ApiModelProperty(name = "좋아요 여부", example = "true / false")
    private Boolean isFavorite;

    @ApiModelProperty(name = "판매 수 카운트", example = "1,2,3")
    private int sellingCount;

    @ApiModelProperty(name = "카테고리", example = "1,2,3")
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
            desList.add(new CustomDescriptionDto(des.getType(), des.getValue()));
        }
        return desList;
    }


}
