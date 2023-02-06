package com.ssafy.fundyou1.member.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.fundyou1.auth.domain.Authority;
import com.ssafy.fundyou1.member.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

    @ApiModelProperty(position = 1, notes = "유저네임아이디", example = "eeee")
    private String loginId;


    @ApiModelProperty(position = 2, notes = "비밀번호", example = "Zjsxlsb123!!")
    private String password;

    @ApiModelProperty(position = 3, notes = "이름", example = "김퐁듀")
    private String username;


    @ApiModelProperty(position = 4, notes = "이미지", example = "프로필 이미지 경로")
    private String profileImg;


    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .username(username)
                .profileImg(profileImg)
                .authority(Authority.ROLE_USER)
                .point(100000)
                .status(true)
                .build();
    }


    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(loginId, password);
    }
}