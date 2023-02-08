package com.ssafy.fundyou1.item.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.fundyou1.cart.entity.CartItem;
import com.ssafy.fundyou1.category.entity.Category;
import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.like.entity.LikeItem;
import lombok.AllArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ToString
public class ItemForm {
    private Long id;
    private int price;

    private String image;

    private String descriptionImg;

    private String title;

    private Boolean isAr;

    private String description;
    
    private String brand;

    private Category category;

    private int sellingCount;


//    public Item toEntity() {
//        return new Item(id, price, image, descriptionImg, title, isAr, description, brand, category);
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescriptionImg() {
        return descriptionImg;
    }

    public void setDescriptionImg(String descriptionImg) {
        this.descriptionImg = descriptionImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsAr() {
        return isAr;
    }

    public void setIsAr(Boolean isAr) {
        this.isAr = isAr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
