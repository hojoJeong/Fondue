package com.ssafy.fundyou1.cart.entity;

import com.ssafy.fundyou1.item.entity.Item;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
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



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item; // FK


    @Column(name = "count")
    private int count;

    @Builder
    public CartItem(Item item, Cart cart, int count) {
        this.item = item;
        this.cart = cart;
        this.count = count;
    }

    // 장바구니에 담을 상품 엔티티 생성 메소드
    public static CartItem createCartItem(Cart cart, Item item, int count) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setCount(count);
        return cartItem;
    }

    // 장바구니에 담을 상품 수량 증가
    public void addCount(int count){
        this.count += count;
    }

    // 장바구니에 담을 수량 반영
    public void updateCount(int count){
        this.count = count;
    }
}

