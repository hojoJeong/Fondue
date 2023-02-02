package com.ssafy.fundyou1.cart.entity;

import com.ssafy.fundyou1.item.entity.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id; //PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart; // FK

    @Column(name = "funding_money")
    private int fundingMoney; //  펀딩 아이템 금액

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item; // FK


    @Column(name = "cart_item_cnt")
    private int cartItemCount;

    public void addCartItemCount(int quantity) {
        this.cartItemCount += quantity;
    }

}

