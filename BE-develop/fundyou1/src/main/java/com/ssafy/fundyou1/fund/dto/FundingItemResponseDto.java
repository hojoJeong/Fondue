package com.ssafy.fundyou1.fund.dto;

import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.item.dto.ItemResponseDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FundingItemResponseDto {
    private Long fundingItemId;
    private ItemResponseDto item;

    private String hostName;
    private Long fundingId;
    // 참여자 수
    private int attendMemberCount;

    // 총가격
    private int itemTotalPrice;

    private int count;

    // 현재 펀딩된 가격
    private int currentFundingPrice;

    private boolean fundingItemStatus;
    private List<String> arImgList;

    @Builder
    public static FundingItemResponseDto createFundingItemDto(FundingItem fundingItem, int attendMemberCount, List<String> arImgList, String hostName) {
        return new FundingItemResponseDto(
                fundingItem.getId(),
                new ItemResponseDto(fundingItem.getItem(), false),
                hostName,
                fundingItem.getFunding().getId(),
                attendMemberCount,
                fundingItem.getItemTotalPrice(),
                fundingItem.getCount(),
                fundingItem.getCurrentFundingPrice(),
                fundingItem.isFundingItemStatus(),
                arImgList
        );
    }


}
