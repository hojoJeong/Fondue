package com.ssafy.fundyou1.item.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RandomItemResponse {
    private long itemId;
    private String image;
    private Boolean isAr;
    private Boolean isFavorite;
    private long price;
    private String title;

}
