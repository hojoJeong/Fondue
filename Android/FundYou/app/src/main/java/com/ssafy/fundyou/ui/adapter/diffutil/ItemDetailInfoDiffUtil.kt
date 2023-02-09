package com.ssafy.fundyou.ui.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.ssafy.fundyou.domain.model.item.ItemDescriptionModel

object ItemDetailInfoDiffUtil : DiffUtil.ItemCallback<ItemDescriptionModel>(){
    override fun areItemsTheSame(
        oldItem: ItemDescriptionModel,
        newItem: ItemDescriptionModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ItemDescriptionModel,
        newItem: ItemDescriptionModel
    ): Boolean {
        return oldItem == newItem
    }
}