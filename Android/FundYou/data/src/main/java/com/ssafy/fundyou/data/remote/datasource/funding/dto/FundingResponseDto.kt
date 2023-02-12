package com.ssafy.fundyou.data.remote.datasource.funding.dto

import com.google.gson.annotations.SerializedName
import com.ssafy.fundyou.data.remote.datasource.item.dto.ItemResponseDto

internal data class FundingResponseDto(
    @SerializedName("currentFundingPrice")
    val currentFundingPrice: Int?,
    @SerializedName("startDate")
    val startDate: Long?,
    @SerializedName("fundingName")
    val fundingName: String,
    @SerializedName("endDate")
    val endDate: Long?,
    @SerializedName("fundingStatus")
    val fundingStatus: Boolean?,
    @SerializedName("fundingId")
    val id: Long?,
    @SerializedName("percentage")
    val percentage: Int?,
    @SerializedName("totalPrice")
    val totalPrice: Int?,
    @SerializedName("fundingItemDtoList")
    val fundingItemList: List<FundingItemResponseDto>?
)