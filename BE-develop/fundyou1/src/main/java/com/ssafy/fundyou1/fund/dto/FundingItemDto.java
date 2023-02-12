package com.ssafy.fundyou1.fund.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.item.dto.CustomDescriptionDto;
import com.ssafy.fundyou1.item.entity.Description;
import com.ssafy.fundyou1.item.dto.ItemResponseDto;
import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.member.entity.Member;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FundingItemDto {
    private Long fundingItemId;
    private ItemResponseDto item;
    private Long fundingId;

    // 총가격
    private int itemTotalPrice;

    private int count;

    // 현재 펀딩된 가격
    private int currentFundingPrice;

    private boolean fundingItemStatus;

    @Builder
    public static FundingItemDto createFundingItemDto(FundingItem fundingItem) {
        return new FundingItemDto(
                fundingItem.getId(),
                new ItemResponseDto(fundingItem.getItem(), false),
                fundingItem.getFunding().getId(),
                fundingItem.getItemTotalPrice(),
                fundingItem.getCount(),
                fundingItem.getCurrentFundingPrice(),
                fundingItem.getFunding().isFundingStatus()
        );
    }


}
