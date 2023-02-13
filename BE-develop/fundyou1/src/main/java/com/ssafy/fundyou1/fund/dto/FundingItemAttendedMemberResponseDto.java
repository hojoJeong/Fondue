package com.ssafy.fundyou1.fund.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FundingItemAttendedMemberResponseDto {
    Long id;
    String senderName;
    int fundingItemPrice;
    String message;

    @Builder
    public FundingItemAttendedMemberResponseDto(Long id, String senderName, int fundingItemPrice, String message) {
        this.id = id;
        this.senderName = senderName;
        this.fundingItemPrice = fundingItemPrice;
        this.message = message;
    }
}
