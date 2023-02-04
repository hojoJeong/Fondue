package com.ssafy.fundyou1.auth.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.fundyou1.member.entity.Member;
import lombok.Getter;

import java.util.Date;
import java.util.Map;

@Getter
public class KakaoSocialLoginResponse {

    @JsonProperty("id")
    private long id;
    @JsonProperty("connected_at")
    private Date connected_at;
    @JsonProperty("properties")
    private  Map<String, Object> properties;
    @JsonProperty("kakao_account")
    private Map<String, Object> kakao_account;


    public Member toEntity() {
        String username = (String) properties.get("nickname");

        String profileImg = (String) kakao_account.get("profile_image_url");

        return Member.builder()
                .loginId("k_" + id)
                .username("k_" + username)
                .password("fundyou"+ id)
                .profileImg(profileImg)
                .point(100000)
                .authority(Authority.ROLE_MEMBER)
                .status(true)
                .build();
    }



}
