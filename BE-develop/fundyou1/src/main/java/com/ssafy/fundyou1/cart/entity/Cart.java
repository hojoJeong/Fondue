package com.ssafy.fundyou1.cart.entity;

import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.member.entity.Member;
import lombok.*;

import javax.persistence.*;

// 장바구니 엔티티
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id; // PK


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // FK


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item; // FK

    @Column(name = "count")
    private int count;


    @Builder
    public Cart(Member member, Item item, int count) {
        this.member = member;
        this.item = item;
        this.count = count;
    }

    // 카트에 유저 할당하여 넣어줌
    public static Cart createCart(Member member, Item item, int count) {
        Cart cart = new Cart();
        cart.setMember(member);
        cart.setItem(item);
        cart.setCount(count);
        return cart;
    }

}

