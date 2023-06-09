package com.ssafy.fundyou.ui.funding_my.model

data class MyFundingItemListUiModel(
    val myFundingOngoingList: List<MyFundingItemInfoUiModel>,
    val myFundingClosedList: List<MyFundingItemInfoUiModel>
)

fun List<MyFundingItemInfoUiModel>.toMyFundingItemListUiModel() : MyFundingItemListUiModel {
    val ongoingList = this.filter { it.status }
    val closedList = this.filter { !it.status }
     return MyFundingItemListUiModel(ongoingList, closedList)
}