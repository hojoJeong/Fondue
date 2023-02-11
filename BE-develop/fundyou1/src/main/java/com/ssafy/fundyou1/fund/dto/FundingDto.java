package com.ssafy.fundyou1.fund.dto;

import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.fund.entity.FundingItem;
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
    private String fundingName;
    private Long startDate;
    private Long endDate;
    private boolean fundingStatus;
    // 총가격
    private int totalPrice;
    // 현재 펀딩된 가격
    private int currentFundingPrice;
    private int percentage;

    public FundingDto(Funding funding, int totalPrice, int currentFundingPrice, int percentage) {
        this.id = funding.getId();
        this.fundingName = funding.getFundingName();
        this.startDate = funding.getStartDate();
        this.endDate = funding.getEndDate();
        this.fundingStatus = funding.isFundingStatus();
        this.totalPrice = totalPrice;
        this.currentFundingPrice = currentFundingPrice;
        this.percentage = percentage;
    }


}
