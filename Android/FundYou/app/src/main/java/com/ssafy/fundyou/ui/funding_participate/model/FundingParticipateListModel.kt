package com.ssafy.fundyou.ui.funding_participate.model

data class FundingParticipateListModel(
    val id: Long,
    val userName: String,
    val participate: Boolean,
    val end: Boolean,
    val date: String,
    val fundingPrice: Int
)