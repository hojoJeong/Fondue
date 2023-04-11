package com.ssafy.fundyou1.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    protected Boolean success;
    protected String message;
}

