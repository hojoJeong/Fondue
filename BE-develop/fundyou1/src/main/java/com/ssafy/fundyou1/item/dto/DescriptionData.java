package com.ssafy.fundyou1.item.dto;

import lombok.Getter;
import lombok.ToString;

/**
 * JSON 안의 JSON 리스트 객체로 파싱받기
 */

@Getter
@ToString
public class DescriptionData {
    private String type;
    private String value;


}

