package com.ssafy.fundyou1.fund.dto;

import lombok.Data;

@Data
public class AddFundingResponseDto {
    Long fundingId;

    String message;

    public AddFundingResponseDto(Long fundingId) {
        this.fundingId = fundingId;
        this.message = "펀딩 아이템이 추가되었습니다.";
    }
}
