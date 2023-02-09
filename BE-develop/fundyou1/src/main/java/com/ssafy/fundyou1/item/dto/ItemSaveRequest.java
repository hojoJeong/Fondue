package com.ssafy.fundyou1.item.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.fundyou1.category.entity.Category;
import com.ssafy.fundyou1.item.entity.Description;
import com.ssafy.fundyou1.item.entity.Item;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
public class ItemSaveRequest {

    @ApiModelProperty(position = 1, notes = "상품 가격", example = "10000")
    @JsonProperty("price")
    private int price;

    @ApiModelProperty(position = 2, notes = "상품 이미지 경로", example = "경로")
    @JsonProperty("image")
    private String image;


    @ApiModelProperty(position = 3, notes = "상품 상세 설명 경로", example = "경로")
    @JsonProperty("description_img")
    private String descriptionImg;


    @ApiModelProperty(position = 4, notes = "상품명", example = "푹신한 쇼파")
    @JsonProperty("title")
    private String title;

    @ApiModelProperty(position = 5, notes = "AR 가능 여부", example = "true or false")
    @JsonProperty("is_ar")
    private Boolean isAr;

    @ApiModelProperty(position = 6, notes = "찜 여부", example = "true or false")
    @JsonProperty("is_favorite")
    private boolean isFavorite;

    @ApiModelProperty(position = 7, notes = "상품 부가 설명", example = "[{\"type\":\"크기\",\"value\":\"10\"},{\"type\":\"색깔\",\"value\":\"red\"}]")
    @JsonProperty("description")
    private List<Description> description;


    @ApiModelProperty(position = 8, notes = "상품 브랜드", example = "한샘")
    @JsonProperty("brand")
    private String brand;

    @ApiModelProperty(position = 9, notes = "카테고리명", example = "인테리어")
    @JsonProperty("category_name")
    private String categoryName;


    public ItemSaveRequest() {
    }

    @Builder
    public ItemSaveRequest(int price, String image, String descriptionImg, String title, Boolean isAr, Boolean isFavorite, List<Description> description, String brand, String categoryName) {
        this.price = price;
        this.image = image;
        this.descriptionImg = descriptionImg;
        this.title = title;
        this.isAr = isAr;
        this.isFavorite = isFavorite;
        this.description = description;
        this.brand = brand;
        this.categoryName= categoryName;
    }

    public Item toItem(Category category) {
        return Item.builder()
                .price(price)
                .image(image)
                .descriptionImg(descriptionImg)
                .title(title)
                .isAr(isAr)
                .isFavorite(isFavorite)
                .sellingCount(0)
                .brand(brand)
                .category(category)
                .build();
    }



}
