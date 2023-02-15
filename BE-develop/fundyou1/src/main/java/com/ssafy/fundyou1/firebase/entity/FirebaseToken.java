package com.ssafy.fundyou1.firebase.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor // 디폴트 생성자 추가
@ToString
public class FirebaseToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private String token;

    private Boolean status;


    @Builder
    public FirebaseToken(Long memberId, String targetToken, Boolean status) {
        this.memberId = memberId;
        this.token = targetToken;
        this.status = status;
    }
}