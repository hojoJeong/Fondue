package com.ssafy.fundyou1.item.dto;

import lombok.Getter;

/**
 * JSON 안의 JSON 리스트 객체로 파싱받기
 */

@Getter
public class DescriptionData {
    private String type;
    private String value;

    @Override
    public String toString(){
        return "type="+ type + "value="+value;
    }
}
