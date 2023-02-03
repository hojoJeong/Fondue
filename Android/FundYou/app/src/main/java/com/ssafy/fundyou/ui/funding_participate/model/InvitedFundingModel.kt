package com.ssafy.fundyou.ui.funding_participate.model

import com.ssafy.fundyou.ui.funding_my.model.FundingItemModel

data class InvitedFundingModel(
    val id: Long,
    val userName: String,
    val fundingItemModel: FundingItemModel
)