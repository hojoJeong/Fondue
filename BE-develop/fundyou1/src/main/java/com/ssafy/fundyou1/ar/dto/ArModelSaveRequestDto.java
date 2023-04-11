package com.ssafy.fundyou1.ar.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class ArModelSaveRequestDto {
    private Long item_id;

    private String url;
}
