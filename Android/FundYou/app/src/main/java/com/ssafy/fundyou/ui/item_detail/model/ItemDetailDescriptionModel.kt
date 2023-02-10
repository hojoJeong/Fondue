package com.ssafy.fundyou.ui.item_detail.model

import com.ssafy.fundyou.domain.model.item.ProductDescriptionModel

data class ItemDetailDescriptionModel(
    val type : String,
    val value : String
)

fun ProductDescriptionModel.toUiModel() = ItemDetailDescriptionModel(
    type = this.type,
    value = this.value
)