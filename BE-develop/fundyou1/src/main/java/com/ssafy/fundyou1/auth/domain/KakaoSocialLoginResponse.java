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
        public boolean profile_nickname_needs_agreement;
        public boolean profile_image_needs_agreement;
        public Profile profile;
        public boolean has_email;
        public boolean email_needs_agreement;
        public boolean is_email_valid;
        public boolean is_email_verified;
        public String email;

        @Getter
        @ToString
        public static class Profile{
            public String nickname;
            public String thumbnail_image_url;
            public String profile_image_url;
            public boolean is_default_image;
        }
    }


    @Getter
    @ToString
    public static class Properties {
        public String nickname;
        public String profile_image;
        public String thumbnail_image;
    }


}
