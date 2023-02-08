package com.ssafy.fundyou1.member.dto.response;

import com.ssafy.fundyou1.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 정보 반환해주는 DTO
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {

    private Long id;

    private String loginId;

    private String username;

    private Boolean status;

    private String profileImg;

    private int point;


    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(
                member.getId(),
                member.getLoginId(),
                member.getUsername(),
                member.isStatus(),
                member.getProfileImg(),
                member.getPoint()
        );
    }


}
