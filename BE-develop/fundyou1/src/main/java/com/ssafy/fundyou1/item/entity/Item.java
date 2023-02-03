package com.ssafy.fundyou1.item.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.fundyou1.cart.entity.CartItem;
import com.ssafy.fundyou1.category.entity.Category;
import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.item.dto.ItemForm;
import com.ssafy.fundyou1.like.entity.LikeItem;
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
    private String description;

    @Column(name = "selling_count",columnDefinition = "integer default 0")
    private int sellingCount;

    @Column(name = "brand")
    private String brand;


    @ManyToOne(targetEntity = Category.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public Item(Long id, int price, String image, String descriptionImg, String title, String isAr, String description, int sellingCount, String brand, Category category) {
        this.id = id;
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

    public static Item createItem(ItemForm dto, Category category) {
        // 예외 발생
        if (dto.getId() != null)
            throw new IllegalStateException("아이템 생성 실패! 아이템의 id가 없어야 합니다");
        if (dto.getCategory().getId() != category.getId())
            throw new IllegalStateException("아이템 생성 실패! 아이템의 id가 잘못되었습니다");

        // 엔티티 생성 및 반환
        return new Item(
            dto.getId(),
                dto.getPrice(),
                dto.getImage(),
                dto.getDescriptionImg(),
                dto.getTitle(),
                dto.getIsAr(),
                dto.getDescription(),
                0,
                dto.getBrand(),
                dto.getCategory()
        );
    }


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
