package com.ssafy.fundyou.ui.funding_paticipation.model

import com.ssafy.fundyou.ui.funding_detail.model.FundingUserModel

data class FundingParticipateModel(
    val id : Int,
    val message : Int,
    val userName : String,
    val fundingPrice : Int
)
