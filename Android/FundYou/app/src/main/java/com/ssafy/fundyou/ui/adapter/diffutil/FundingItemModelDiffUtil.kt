package com.ssafy.fundyou.ui.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.ssafy.fundyou.ui.myfunding.model.FundingItemModel

object FundingItemModelDiffUtil : DiffUtil.ItemCallback<FundingItemModel>(){
    override fun areItemsTheSame(
        oldItem: FundingItemModel,
        newItem: FundingItemModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: FundingItemModel,
        newItem: FundingItemModel
    ): Boolean {
        return oldItem == newItem
    }
}