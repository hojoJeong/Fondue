package com.ssafy.fundyou1.item.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ItemListSaveRequestDto {
    private List<ItemSaveRequest> requestList;
}
