package com.ssafy.fundyou1.member.dto.response;

import com.ssafy.fundyou1.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

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
                member.getStatus(),
                member.getProfileImg(),
                member.getPoint()
        );
    }

    public MemberResponseDto(Long id, String loginId, String username, Boolean status, String profileImg, int point) {
        this.id = id;
        this.loginId = loginId;
        this.username = username;
        this.status = status;
        this.profileImg = profileImg;
        this.point = point;
    }
}
