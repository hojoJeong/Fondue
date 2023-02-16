package com.ssafy.fundyou1.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ItemListSaveRequestDto {
    @JsonProperty("requestList")
    private List<ItemSaveRequest> requestList;
}
