package com.ssafy.fundyou1.member.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.fundyou1.auth.domain.Authority;
import com.ssafy.fundyou1.cart.entity.Cart;
import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.global.domain.BaseEntity;
import com.ssafy.fundyou1.global.exception.BusinessException;
import com.ssafy.fundyou1.global.exception.ErrorCode;
import com.ssafy.fundyou1.like.entity.Like;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "MEMBER")
@Getter
@Entity
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;


    @Column(name = "login_id", length = 32, nullable = false)
    private String loginId;


    private Boolean status;

    private String username;

    private String profileImg;


    @Column(name="point",columnDefinition = "int default 100000")
    private int point;

    private String password;


    @OneToOne(mappedBy = "member",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "like_id")
    private Like like; //찜 정보

    @OneToOne(mappedBy = "member",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart; //장바구니 정보

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Funding> fundings = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private Authority authority;


    protected Member() {
    }

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



    public static Member createMember(String loginId, String username, String password, String profileImg) {
        return Member.builder()
                .loginId(loginId)
                .username(username)
                .password(password)
                .profileImg(profileImg)
                .point(100000)
                .authority(Authority.ROLE_MEMBER)
                .status(true)
                .build();
    }

    public static Member deleteMember(String loginId) {
        return Member.builder()
                .loginId(loginId)
                .authority(Authority.ROLE_MEMBER)
                .status(false)
                .build();
    }


    public void changeStatus(String loginId) {
        this.loginId= loginId;
        this.status = false;
    }



    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void checkPassword(PasswordEncoder passwordEncoder, String password) {
        if (!passwordEncoder.matches(password, this.password)) {
            throw new BusinessException(ErrorCode.MEMBER_LOGIN_ERROR_BY_PASSWORD);
        }
    }



}
