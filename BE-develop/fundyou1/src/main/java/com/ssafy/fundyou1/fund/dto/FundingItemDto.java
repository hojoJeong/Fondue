package com.ssafy.fundyou1.fund.dto;

import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class FundingItemDto {
    private Long id;

    // 펀딩 아이템
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="item_id")
    private Item item; // FK

    // 펀딩 아이디
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="funding_id")
    private Funding funding; // FK


    // 총가격
    private int itemTotalPrice;


    private int count;

    // 현재 펀딩된 가격
    // 펀딩 참여자 DB 다 돌아서 sum 해준 값

    private boolean fundingItemStatus;

    // 참여자 수
    // 펀딩 참여자 DB 갯수 카운트 - @Query문 쓰면 되려나

}
