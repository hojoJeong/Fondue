package com.ssafy.fundyou1.fund.mapper;

import com.ssafy.fundyou1.fund.dto.FundingItemDto;

public interface FundingMapper {

    // 펀딩 상품 정보
    public FundingItemDto getFundingItemsInfo(Long userId);

}
