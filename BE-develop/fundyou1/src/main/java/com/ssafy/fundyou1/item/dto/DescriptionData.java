package com.ssafy.fundyou1.item.dto;

import com.ssafy.fundyou1.category.entity.Category;
import com.ssafy.fundyou1.item.entity.Description;
import com.ssafy.fundyou1.item.entity.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * JSON 안의 JSON 리스트 객체로 파싱받기
 */

@Getter
@ToString
public class DescriptionData {

    private Item item;
    private String type;
    private String value;


    @Builder
    public DescriptionData(Item item, String type, String value) {
        this.item = item;
        this.type = type;
        this.value = value;
    }



}

