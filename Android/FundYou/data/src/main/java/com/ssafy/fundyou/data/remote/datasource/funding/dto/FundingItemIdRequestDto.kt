package com.ssafy.fundyou.data.remote.datasource.funding.dto

import com.google.gson.annotations.SerializedName

data class FundingItemIdRequestDto(
    @SerializedName("funding_item_id")
    val fundingItemId : Long
)