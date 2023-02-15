package com.ssafy.fundyou1.firebase.entity;

import lombok.*;

import javax.persistence.*;

//테스트 커밋 주석
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


    @Builder
    public FirebaseToken(Long memberId, String targetToken) {
        this.memberId = memberId;
        this.token = token;
    }
}