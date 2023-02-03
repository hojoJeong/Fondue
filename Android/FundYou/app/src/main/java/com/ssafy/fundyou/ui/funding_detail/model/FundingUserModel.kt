package com.ssafy.fundyou.ui.funding_detail.model

data class FundingUserModel(
    val id : Int,
    val userName : String,
    val tempUserImg : Int,
    val fundingTotalPrice : Int,
    val fundingItem : List<String>
)