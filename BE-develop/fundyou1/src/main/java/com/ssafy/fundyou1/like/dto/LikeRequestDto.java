package com.ssafy.fundyou1.like.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//  찜에 담을 상품의 itemid를 전달받음
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikeRequestDto {

    private Long itemId;

}