package com.ssafy.fundyou1.member.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.fundyou1.auth.domain.Authority;
import com.ssafy.fundyou1.cart.entity.Cart;
import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.global.domain.BaseEntity;
import com.ssafy.fundyou1.like.entity.Like;
import com.ssafy.fundyou1.member.dto.response.MemberResponseDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


// 회원 엔티티
@Table(name = "MEMBER")
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;


    @Column(name = "login_id", length = 32, nullable = false)
    private String loginId;
    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "member_status")
    private boolean status;

    @Column(name = "profileImg")
    private String profileImg;


    @Column(name="point",columnDefinition = "int default 100000")
    private int point;

    @Column(name = "mail")
    private String mail;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private Authority authority;


    @OneToOne(mappedBy = "member",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "like_id")
    @JsonIgnore
    private Like like; //찜 정보

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Cart> carts; //장바구니 정보

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Funding> fundings = new ArrayList<>();

    @Builder
    public Member(String loginId, String password,String username,String profileImg, int point, Authority authority, boolean status) {
        this.loginId = loginId;
        this.password = password;
        this.username = username;
        this.profileImg = profileImg;
        this.point = point;
        this.authority = authority;
        this.status = status;
    }
}
