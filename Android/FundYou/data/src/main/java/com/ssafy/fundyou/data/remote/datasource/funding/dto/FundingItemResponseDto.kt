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
    @SerializedName("fundingId")
    val fundingId : Long,
    @SerializedName("fundingItemId")
    val id : Long?,
    @SerializedName("attendMemberCount")
    val participateCount : Int?,
    @SerializedName("item")
    val itemInfo : ItemResponseDto
)