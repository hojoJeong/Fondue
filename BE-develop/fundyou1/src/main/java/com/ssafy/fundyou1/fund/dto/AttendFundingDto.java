package com.ssafy.fundyou1.fund.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class AttendFundingDto {
    private Long fundingItemId;
    private int point;

    private String message;

}
