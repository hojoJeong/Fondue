package com.ssafy.fundyou1.fund.dto;

import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FundingSaveRequest {

    private Member member;
    private LocalDateTime startDate;

    //    private LocalDateTime endDate;
    private boolean fundingStatus;

    public Funding toFunding() {
        Funding build = Funding.builder()
                .id(id)
                .member(member)
                .fundingStatus(fundingStatus)
                .build();
        return build;
    }

    @Builder
    public FundingSaveRequest(Long id, Member member, LocalDateTime startDate) {
        this.id = id;
        this.member = member;
        this.startDate = startDate;
//        this.endDate = endDate;
        this.fundingStatus = true;
    }
}
