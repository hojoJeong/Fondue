package com.ssafy.fundyou1.item.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.fundyou1.cart.entity.Cart;
import com.ssafy.fundyou1.category.entity.Category;
import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.like.entity.LikeItem;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor // 디폴트 생성자 추가
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @Column(name = "price")
    private int price;

    @Column(name = "image") // 상품이미지 - 이미지 엔티티 만들어서 따로 처리
    private String image;

    @Column(name = "description_img") // 상세 이미지 - 이미지 엔티티 만들어서 따로 처리
    private String descriptionImg;
    @Column(name = "title")
    private String title;

    @Column(name = "is_ar")
    private String isAr;

    @Column(name = "description")
    @ElementCollection
    private List<String>  description;
    @Column(name = "selling_count",columnDefinition = "integer default 0")
    private int sellingCount;

    @Column(name = "brand")
    private String brand;

    @JsonIgnore
    @OneToMany(mappedBy = "item")
    private List<Cart> carts = new ArrayList<>();


    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public Item(int price, String image, String descriptionImg, String title, String isAr, List<String> description, int sellingCount, String brand, Category category) {
        this.price = price;
        this.image = image;
        this.descriptionImg = descriptionImg;
        this.title = title;
        this.isAr = isAr;
        this.description = description;
        this.sellingCount = sellingCount;
        this.brand = brand;
        this.category = category;
    }


    @JsonIgnore
    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL)
    List<LikeItem> likeItems = new ArrayList<>();



    @JsonIgnore
    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL)
    List<FundingItem> fundingItems = new ArrayList<>();


    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", descriptionImg='" + descriptionImg + '\'' +
                ", title='" + title + '\'' +
                ", isAr='" + isAr + '\'' +
                ", description='" + description + '\'' +
                ", sellingCount=" + sellingCount +
                ", brand='" + brand + '\'' +
                ", category=" + category +
                ", likeItems=" + likeItems +
                ", carts=" + carts +
                ", fundingItems=" + fundingItems +
                '}';
    }
}
