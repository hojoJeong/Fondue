package com.ssafy.fundyou1.auth.domain;

import lombok.Getter;

@Getter
public enum Provider {

    FUDYOU("fundyou", 1),
    GOOGLE("google", 2),
    KAKAO("kakao", 3);

    private final String providerName;
    private final Integer providerNumber;

    Provider(String providerName, Integer providerNumber) {
        this.providerName = providerName;
        this.providerNumber = providerNumber;
    }
}
