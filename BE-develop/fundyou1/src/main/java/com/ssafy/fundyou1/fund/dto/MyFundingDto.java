package com.ssafy.fundyou1.fund.dto;

import com.ssafy.fundyou1.fund.entity.Funding;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyFundingDto {
    private Long fundingId;
    private String fundingName;
    private Long startDate;
    private Long endDate;
    private boolean fundingStatus;
    // 총가격
    private int totalPrice;
    // 현재 펀딩된 가격
    private int currentFundingPrice;
    private int percentage;
    private List<FundingItemResponseDto> fundingItemResponseDtoList;

    public MyFundingDto(Funding funding, int totalPrice, int currentFundingPrice, List<FundingItemResponseDto> fundingItemResponseDtoList) {
        this.fundingId = funding.getId();
        this.fundingName = funding.getFundingName();
        this.startDate = funding.getStartDate();
        this.endDate = funding.getEndDate();
        this.fundingStatus = funding.isFundingStatus();
        this.totalPrice = totalPrice;
        this.currentFundingPrice = currentFundingPrice;
        this.percentage = (currentFundingPrice * 100 / totalPrice);
        this.fundingItemResponseDtoList = fundingItemResponseDtoList;
    }
}
