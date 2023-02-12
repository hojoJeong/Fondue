package com.ssafy.fundyou.ui.funding_my_detail.model

import com.ssafy.fundyou.domain.model.funding.FundingStatisticsModel
import com.ssafy.fundyou.ui.funding_my.model.FundingParticipateModel

data class FundingParticipateUserUiModel(
    val participateUserName : String,
    val participateUserProfileImg : String,
    val fundingPrice : Int
)

fun FundingStatisticsModel.toParticipateUserUiModel() = FundingParticipateUserUiModel(
    participateUserName = this.fundingParticipateMemberName,
    participateUserProfileImg = this.fundingParticipateMemberProfileImg,
    fundingPrice = this.fundingPrice
)