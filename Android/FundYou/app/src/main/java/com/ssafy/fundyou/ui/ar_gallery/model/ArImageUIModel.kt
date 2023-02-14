package com.ssafy.fundyou.ui.ar_gallery.model

import com.ssafy.fundyou.domain.model.ar.ArImageModel

data class ArImageUIModel(
    val fundingItemId: Long,
    val url: String
)

fun ArImageModel.toArImageUIModel() = ArImageUIModel(
    fundingItemId = this.fundingItemId,
    url = this.url
)