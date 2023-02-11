package com.ssafy.fundyou.domain.model.funding

data class FundingItemModel(
    val id : Long,
    val itemCount : Int,
    val currentFundingPrice : Int,
    val status : Boolean,
    val participantsCount : Int,
    val itemPrice : Int,
)