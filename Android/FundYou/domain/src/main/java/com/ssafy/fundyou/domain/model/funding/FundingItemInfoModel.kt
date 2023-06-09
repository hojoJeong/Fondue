package com.ssafy.fundyou.domain.model.funding

import com.ssafy.fundyou.domain.model.item.ProductItemModel

data class FundingItemInfoModel(
    val id : Long,
    val itemCount : Int,
    val fundingHostUserName : String,
    val currentFundingPrice : Int,
    val status : Boolean,
    val participantsCount : Int,
    val itemTotalPrice : Int,
    val arImgList : List<String>,
    val info : ProductItemModel
)

