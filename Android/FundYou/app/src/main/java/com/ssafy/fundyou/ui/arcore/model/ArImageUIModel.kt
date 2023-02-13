package com.ssafy.fundyou.ui.arcore.model

import com.ssafy.fundyou.domain.model.ar.ArImageModel

data class ArImageUIModel(
    val fundingId: Long,
    val arId: Long,
    val memberId: Long,
    val itemId: Long,
    val url: String
)

fun ArImageModel.toArImageUIModel() = ArImageUIModel(
    fundingId = this.fundingId,
    arId = this.arId,
    memberId = this.memberId,
    itemId = this.itemId,
    url = this.url
)