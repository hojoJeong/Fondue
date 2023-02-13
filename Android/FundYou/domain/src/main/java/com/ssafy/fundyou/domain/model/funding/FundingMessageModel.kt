package com.ssafy.fundyou.domain.model.funding

data class FundingMessageModel(
    val id : Long,
    val senderName : String,
    val fundingPrice : Int,
    val message : String
)