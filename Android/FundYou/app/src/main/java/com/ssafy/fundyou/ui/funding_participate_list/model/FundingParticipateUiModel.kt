package com.ssafy.fundyou.ui.funding_invited_list.model

import com.ssafy.fundyou.domain.model.funding.FundingParticipateModel

data class FundingParticipateListUiModel(
    val endDate: Long,
    val fundingHostName: String,
    val fundingPoint: Int,
    val fundingStatus: Boolean,
    val id: Long,
    val startDate: Long,
    val fundingHostProfile: String
)

fun FundingParticipateModel.toUiModel() = FundingParticipateListUiModel(
    endDate = this.endDate,
    fundingHostName = this.fundingHostName,
    fundingPoint = this.fundingPoint,
    fundingStatus = this.fundingStatus,
    id = this.id,
    startDate = this.startDate,
    fundingHostProfile = this.fundingHostProfile
)