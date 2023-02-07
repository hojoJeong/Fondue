package com.ssafy.fundyou1.fund.dto;

import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FundingItemDto {
    private Long id;
    private Item item; // FK
    private Funding funding; // FK

    // 총가격
    private int itemTotalPrice;

    private int count;

    // 현재 펀딩된 가격
    // 펀딩 참여자 DB 다 돌아서 sum 해준 값
    private boolean fundingItemStatus;
    public static FundingItemDto createFundingItemDto(FundingItem fundingItem) {
        return new FundingItemDto(
                fundingItem.getId(),
                fundingItem.getItem(),
                fundingItem.getFunding(),
                fundingItem.getItemTotalPrice(),
                fundingItem.getCount(),
                fundingItem.getFunding().isFundingStatus()
        );
    }
// 참여자 수
    // 펀딩 참여자 DB 갯수 카운트 - @Query문 쓰면 되려나

}
