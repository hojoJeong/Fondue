package com.ssafy.fundyou.ui.funding_my_list.model

data class MyFundingListModel(
    val id : Long,
    val sequence : Int,
    val startDate : Long,
    val endDate : Long,
    val status : Boolean,
    val currentFundingPrice : Int,
    val totalFundingPrice : Int,
    val imgList : List<String>
)