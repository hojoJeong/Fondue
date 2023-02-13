package com.ssafy.fundyou.ui.common.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.ssafy.fundyou.ui.item_detail.model.ItemDetailDescriptionModel

object ItemDescriptionModelDiffUtil : DiffUtil.ItemCallback<ItemDetailDescriptionModel>() {
    override fun areItemsTheSame(
        oldItem: ItemDetailDescriptionModel,
        newItem: ItemDetailDescriptionModel
    ): Boolean {
        return oldItem.type == newItem.type
    }

    override fun areContentsTheSame(
        oldItem: ItemDetailDescriptionModel,
        newItem: ItemDetailDescriptionModel
    ): Boolean {
        return oldItem == newItem
    }
}