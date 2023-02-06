package com.ssafy.fundyou1.search.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class SearchKeyWord {
    private String keyword;
    private int min_price;
    private int max_price;

}
