package com.ssafy.fundyou.data.remote.datasource.funding.dto

import com.google.gson.annotations.SerializedName
import dagger.multibindings.IntoMap

data class FundingItemParticipateResponseDto(
    @SerializedName("id")
    val id : Long?,
    @SerializedName("senderName")
    val senderName : String?,
    @SerializedName("fundingItemPrice")
    val fundingItemPrice : Int?,
    @SerializedName("message")
    val message : String?
)