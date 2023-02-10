package com.ssafy.fundyou1.firebase.entity;

import com.ssafy.fundyou1.auth.domain.Authority;
import lombok.*;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Firebase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "firebase_id")
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "target_token")
    private String targetToken;


    @Builder
    public Firebase(Long memberId, String targetToken) {

    }
}