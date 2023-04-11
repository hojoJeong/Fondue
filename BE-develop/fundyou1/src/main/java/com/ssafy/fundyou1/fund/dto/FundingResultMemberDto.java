package com.ssafy.fundyou1.fund.dto;

import com.ssafy.fundyou1.member.entity.Member;
import lombok.*;

import javax.persistence.GeneratedValue;

@Setter
@NoArgsConstructor
@Getter
@ToString
public class FundingResultMemberDto {

    private Long memberId;
    private String username;
    private String profileImg;
    private int attendedPrice;


    @Builder
    public FundingResultMemberDto(Member member, int attendedPrice) {
        this.memberId = member.getId();
        this.username = member.getUsername();
        this.profileImg = member.getProfileImg();
        this.attendedPrice = attendedPrice;
    }

}
