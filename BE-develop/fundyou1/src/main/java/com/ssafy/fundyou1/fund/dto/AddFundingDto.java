package com.ssafy.fundyou1.fund.dto;

import lombok.Data;

@Data
public class AddFundingDto {

    String message;

    public AddFundingDto() {
        this.message = "펀딩 아이템이 추가되었습니다.";
    }
}
