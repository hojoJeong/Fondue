package com.ssafy.fundyou1.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.fundyou1.cart.entity.Cart;
import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.item.entity.Description;
import com.ssafy.fundyou1.item.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ItemDto {

    private Long id;

    private int price;

    private String image;

    private String descriptionImg;

    private String title;

    private Boolean isAr;

    private List<Description> description;

    private int sellingCount;

    private String brand;

    @JsonProperty("category_id")
    private Long categoryId;

    public ItemDto(Long id, int price, String image, String descriptionImg, String title, Boolean isAr, List<Description> description, int sellingCount, String brand, Long id1) {
        this.id = id;
        this.price = price;
        this.image = image;
        this.descriptionImg = descriptionImg;
        this.title = title;
        this.isAr = isAr;
        this.description = description;
        this.sellingCount = sellingCount;
        this.brand = brand;
        this.categoryId = id1;

    }

    public static ItemDto createItemDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getPrice(),
                item.getImage(),
                item.getDescriptionImg(),
                item.getTitle(),
                item.getIsAr(),
                item.getDescriptions(),
                item.getSellingCount(),
                item.getBrand(),
                item.getCategory().getId()
        );
    }


    public void addsellingCount(int quantity) {
        this.sellingCount += quantity;
    }


    List<Cart> carts = new ArrayList<>();

    List<FundingItem> fundingItems = new ArrayList<>();
}
