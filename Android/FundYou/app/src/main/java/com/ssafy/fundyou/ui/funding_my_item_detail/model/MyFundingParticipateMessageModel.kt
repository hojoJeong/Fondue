package com.ssafy.fundyou.ui.funding_my_item_detail.model

import com.ssafy.fundyou.domain.model.funding.FundingMessageModel

data class MyFundingParticipateMessageModel(
    val id : Long,
    val fundingPrice : Int,
    val senderName : String,
    val message : String
)

fun FundingMessageModel.toFundingParticipateUserModel() = MyFundingParticipateMessageModel(
    id = this.id,
    fundingPrice = this.fundingPrice,
    senderName = this.senderName,
    message = this.message
)
