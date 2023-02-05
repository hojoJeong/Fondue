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


}
