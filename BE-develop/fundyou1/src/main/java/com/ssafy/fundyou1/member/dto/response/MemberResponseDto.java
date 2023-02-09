package com.ssafy.fundyou1.member.dto.response;

import com.ssafy.fundyou1.member.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 정보 반환해주는 DTO
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {

    @ApiModelProperty(position = 1, notes = "회원고유PK", example = "1,2,3")
    private Long id;
    @ApiModelProperty(position = 1, notes = "회원아이디(카카오고유id)", example = "1239876")
    private String loginId;
    @ApiModelProperty(position = 1, notes = "카카오 닉네임", example = "김싸피")
    private String username;
    @ApiModelProperty(position = 1, notes = "탈퇴여부", example = "true/false")
    private Boolean status;
    @ApiModelProperty(position = 1, notes = "프로필이미지", example = "kakao/img/profile.jpg")
    private String profileImg;
    @ApiModelProperty(position = 1, notes = "회원포인트", example = "100000")
    private int point;
    @ApiModelProperty(position = 1, notes = "회원이메일(카카오 이메일)", example = "eeee@naver.com")
    private String mail;


    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(
                member.getId(),
                member.getLoginId(),
                member.getUsername(),
                member.isStatus(),
                member.getProfileImg(),
                member.getPoint(),
                member.getMail()
        );
    }


}
