package com.ssafy.fundyou.ui.common.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.ssafy.fundyou.ui.item_detail.model.ItemDetailModel

object ItemDetailInfoDiffUtil : DiffUtil.ItemCallback<ItemDetailModel>(){
    override fun areItemsTheSame(
        oldItem: ItemDetailModel,
        newItem: ItemDetailModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ItemDetailModel,
        newItem: ItemDetailModel
    ): Boolean {
        return oldItem == newItem
    }
}