package com.ssafy.fundyou.domain.model.funding

data class FundingTotalModel(
    val fundingInfo: FundingInfoModel,
    val fundingItemList: List<FundingItemInfoModel>
)