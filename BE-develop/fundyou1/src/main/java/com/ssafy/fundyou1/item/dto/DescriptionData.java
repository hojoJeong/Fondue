package com.ssafy.fundyou1.item.dto;

import lombok.Getter;

@Getter
public class DescriptionData {
    private String type;
    private String value;

    @Override
    public String toString(){
        return "type="+ type + "value="+value;
    }
}
