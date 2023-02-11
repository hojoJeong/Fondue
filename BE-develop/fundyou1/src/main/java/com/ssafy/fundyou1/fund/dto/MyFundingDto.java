package com.ssafy.fundyou1.fund.dto;

import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.fund.entity.FundingItem;
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
    private Long id;
    private Long endDate;
    private boolean fundingStatus;
    // 총가격
    private int totalPrice;
    // 현재 펀딩된 가격
    private int currentFundingPrice;
    private int percentage;
    private List<FundingItemDto> fundingItemDtoList;

    public MyFundingDto(Funding funding, int totalPrice, int currentFundingPrice, int percentage, List<FundingItemDto> fundingItemDtoList) {
        this.id = funding.getId();
        this.endDate = funding.getEndDate();
        this.fundingStatus = funding.isFundingStatus();
        this.totalPrice = totalPrice;
        this.currentFundingPrice = currentFundingPrice;
        this.percentage = percentage;
        this.fundingItemDtoList = fundingItemDtoList;
    }
}
