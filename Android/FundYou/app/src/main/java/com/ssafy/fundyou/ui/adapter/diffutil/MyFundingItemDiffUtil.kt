package com.ssafy.fundyou.ui.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.ssafy.fundyou.ui.funding_my.model.MyFundingItemInfoUiModel

object MyFundingItemDiffUtil : DiffUtil.ItemCallback<MyFundingItemInfoUiModel>() {
    override fun areItemsTheSame(
        oldItem: MyFundingItemInfoUiModel,
        newItem: MyFundingItemInfoUiModel
    ): Boolean {
        return oldItem.fundingItemId == newItem.fundingItemId
    }

    override fun areContentsTheSame(
        oldItem: MyFundingItemInfoUiModel,
        newItem: MyFundingItemInfoUiModel
    ): Boolean {
        return oldItem == newItem
    }
}