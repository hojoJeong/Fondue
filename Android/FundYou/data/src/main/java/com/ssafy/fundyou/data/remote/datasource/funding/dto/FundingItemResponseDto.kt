package com.ssafy.fundyou.data.remote.datasource.funding.dto

import com.google.gson.annotations.SerializedName
import com.ssafy.fundyou.data.remote.datasource.item.dto.ItemResponseDto

internal data class FundingItemResponseDto(
    @SerializedName("count")
    val count : Int?,
    @SerializedName("currentFundingPrice")
    val currentFundingPrice : Int?,
    @SerializedName("fundingItemStatus")
    val fundingItemStatus : Boolean?,
    @SerializedName("funding_id")
    val fundingId : Long,
    @SerializedName("id")
    val id : Long,
    @SerializedName("item")
    val itemInfo : ItemResponseDto
)