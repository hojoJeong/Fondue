package com.ssafy.fundyou1.auth.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.fundyou1.member.entity.Member;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

@Getter
public class KakaoSocialLoginResponse {

    private Long id;
    private Date connected_at;
    private Properties properties;
    private KakaoAccount kakao_account;



    @Getter
    @ToString
    public static class KakaoAccount {
        private String profile_image_url;
    }


    @Getter
    @ToString
    public static class Properties {
        private String nickname;
    }



//    public Member toEntity(PasswordEncoder passwordEncoder) {
//        String username = (String) properties.get("nickname");
//
//        String profileImg = (String) kakao_account.get("profile_image_url");
//
//        return Member.builder()
//                .loginId(id)
//                .username(username)
//                .password("fundyou"+ id)
//                .profileImg(profileImg)
//                .point(100000)
//                .authority(Authority.ROLE_USER)
//                .status(true)
//                .build();
//    }
//

}
