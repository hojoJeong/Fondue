package com.ssafy.fundyou.ui.funding_participate_item.model

import com.ssafy.fundyou.domain.model.funding.FundingItemInfoModel
import com.ssafy.fundyou.domain.model.item.ProductItemModel
import com.ssafy.fundyou.ui.funding_participate.model.FundingParticipateItemUiModel
import com.ssafy.fundyou.ui.item_detail.model.ItemDetailModel
import com.ssafy.fundyou.ui.item_detail.model.toItemDetailModel
import kotlin.math.roundToInt

data class FundingParticipateItemDetailUiModel(
    val id : Long,
    val arRegistered: Boolean,
    val itemImgList : List<String>,
    val itemInfo : ItemDetailModel
)

fun FundingItemInfoModel.toFundingParticipateItemDetailUiModel() = FundingParticipateItemDetailUiModel(
    id = this.id,
    arRegistered = this.arImgList.isNotEmpty(),
    itemInfo = this.info.toItemDetailModel(),
    itemImgList = addAllImageList(this.arImgList, this.info.img),
)

fun addAllImageList(arImgList : List<String>, itemImgList : List<String>) : List<String>{
    val allImageList = mutableListOf<String>()

    allImageList.addAll(arImgList)
    allImageList.addAll(itemImgList)

    return allImageList
}