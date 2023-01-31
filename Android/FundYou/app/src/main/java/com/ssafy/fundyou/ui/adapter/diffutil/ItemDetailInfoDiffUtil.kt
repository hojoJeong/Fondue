package com.ssafy.fundyou.ui.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.ssafy.fundyou.ui.itemdetail.model.ItemDetailInfoModel

object ItemDetailInfoDiffUtil : DiffUtil.ItemCallback<ItemDetailInfoModel>(){
    override fun areItemsTheSame(
        oldItem: ItemDetailInfoModel,
        newItem: ItemDetailInfoModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ItemDetailInfoModel,
        newItem: ItemDetailInfoModel
    ): Boolean {
        return oldItem == newItem
    }
}