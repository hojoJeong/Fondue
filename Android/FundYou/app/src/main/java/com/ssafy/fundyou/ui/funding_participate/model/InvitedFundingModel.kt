package com.ssafy.fundyou.ui.funding_participate.model

import java.util.*

data class InvitedFundingModel(
    val id: Long,
    val userName: String,
    val participate: Boolean,
    val end: Boolean,
    val date: String,
    val fundingPrice: Int
)