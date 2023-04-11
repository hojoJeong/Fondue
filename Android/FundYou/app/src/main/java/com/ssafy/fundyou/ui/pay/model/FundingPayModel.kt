package com.ssafy.fundyou.ui.pay.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FundingPayModel(
    val username: String,
    val productImg: Int,
    val brand: String,
    val name: String,
    val price: Int,
    val balance: Int,
    val percent: Int,
    val remainPoint: Int,
    val fundingPrice: Int
) : Parcelable