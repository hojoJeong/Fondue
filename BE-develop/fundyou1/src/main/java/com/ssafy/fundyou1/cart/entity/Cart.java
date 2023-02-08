package com.ssafy.fundyou1.cart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.fundyou1.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funding_cart_id")
    private long id; // PK


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // FK

//    @JsonIgnore
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cart", cascade = CascadeType.ALL)
//    private List<CartItem> cartItems = new ArrayList<>(); // funding_cart_item table의 fundingCart 의해 mapping
//

    @Builder
    public Cart(Member member){
        this.member = member;
    }


    // 카트에 유저 할당하여 넣어줌
    // 회원 한명당 장바구니를 하나씩 갖기 때문에 할당해주는거임
    public static Cart createCart(Member member){
        Cart cart = new Cart();
        cart.setMember(member);
        return cart;
    }


}

