package com.ssafy.fundyou.ui.funding_participate.model

import com.ssafy.fundyou.domain.model.funding.FundingHostInfoModel

data class FundingHostUiModel(
    val fundingName : String,
    val userProfileImg : String,
    val userName : String
)

fun FundingHostInfoModel.toInvitedFondueHostInfoUiModel() = FundingHostUiModel(
    fundingName = this.fundingName,
    userProfileImg = this.fundingHostProfileImg,
    userName = this.fundingHostName
)