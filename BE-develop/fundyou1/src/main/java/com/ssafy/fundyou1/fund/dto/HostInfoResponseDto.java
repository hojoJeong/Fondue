package com.ssafy.fundyou1.fund.dto;

import com.ssafy.fundyou1.member.entity.Member;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class HostInfoResponseDto {
    String profileImg;
    String hostName;
    String fundingName;

    public HostInfoResponseDto(Member member, String fundingName) {
        this.profileImg = member.getProfileImg();
        this.hostName = member.getUsername();
        this.fundingName = fundingName;
    }
}
