package com.ssafy.fundyou1.member.entity;



import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.fundyou1.cart.entity.Cart;
import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.global.jpa.BaseEntity;
import com.ssafy.fundyou1.like.entity.Like;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name="point" , columnDefinition = "integer default 100000")
    private int point;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "like_id")
    private Like like; //찜 정보

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart; //장바구니 정보

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Funding> fundings = new ArrayList<>();


}
