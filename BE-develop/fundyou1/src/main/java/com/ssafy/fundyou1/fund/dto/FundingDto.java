package com.ssafy.fundyou1.fund.dto;

import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FundingDto {

    private Long id;
    private Member member;
    private LocalDateTime startDate;

//    private LocalDateTime endDate;
    private boolean fundingStatus;

    public Funding toEntity() {
        Funding build = Funding.builder()
                .id(id)
                .member(member)
                .fundingStatus(fundingStatus)
                .build();
        return build;
    }

    @Builder
    public FundingDto(Long id, Member member, LocalDateTime startDate) {
        this.id = id;
        this.member = member;
        this.startDate = startDate;
//        this.endDate = endDate;
        this.fundingStatus = true;
    }


}
