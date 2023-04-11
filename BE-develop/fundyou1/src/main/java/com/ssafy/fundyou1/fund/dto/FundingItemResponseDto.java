package com.ssafy.fundyou1.fund.dto;

import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.item.dto.ItemResponseDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FundingItemResponseDto {
    private Long fundingItemId;
    private ItemResponseDto item;
    private Long fundingId;
    // 참여자 수
    private int attendMemberCount;

    // 총가격
    private int itemTotalPrice;

    private int count;

    // 현재 펀딩된 가격
    private int currentFundingPrice;

    private boolean fundingItemStatus;

    @Builder
    public static FundingItemResponseDto createFundingItemDto(FundingItem fundingItem, int attendMemberCount) {
        return new FundingItemResponseDto(
                fundingItem.getId(),
                new ItemResponseDto(fundingItem.getItem(), false),
                fundingItem.getFunding().getId(),
                attendMemberCount,
                fundingItem.getItemTotalPrice(),
                fundingItem.getCount(),
                fundingItem.getCurrentFundingPrice(),
                fundingItem.isFundingItemStatus()
        );
    }


}
