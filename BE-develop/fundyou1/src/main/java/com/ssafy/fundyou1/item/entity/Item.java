package com.ssafy.fundyou1.item.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.fundyou1.cart.entity.CartItem;
import com.ssafy.fundyou1.category.entity.Category;
import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.like.entity.LikeItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
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
    private String description;

    @Column(name = "selling_count",columnDefinition = "integer default 0")
    private int sellingCount;

    @Column(name = "brand")
    private String brand;

    @ManyToOne(targetEntity = Category.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


    public void addsellingCount(int quantity) {
        this.sellingCount += quantity;
    }


    @JsonIgnore
    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL)
    List<LikeItem> likeItems = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL)
    List<CartItem> cartItems = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL)
    List<FundingItem> fundingItems = new ArrayList<>();



}
