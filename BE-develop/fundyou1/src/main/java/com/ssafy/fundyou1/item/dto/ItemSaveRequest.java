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

    @JsonProperty("price")
    private int price;

    @JsonProperty("image")
    private String image;


    @JsonProperty("description_img")
    private String descriptionImg;


    @JsonProperty("title")
    private String title;

    @JsonProperty("is_ar")
    private Boolean isAr;

    @JsonProperty("is_favorite")
    private Boolean isFavorite;

    @JsonProperty("description")
    private List<Description> description;

    @JsonProperty("brand")
    private String brand;

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
