package com.ssafy.fundyou.ui.pay.model

data class FundingPayModel(
    val productImg: Int,
    val brand: String,
    val name: String,
    val price: Int,
    val balance: Int,
    val remainPoint: Int
) {
}