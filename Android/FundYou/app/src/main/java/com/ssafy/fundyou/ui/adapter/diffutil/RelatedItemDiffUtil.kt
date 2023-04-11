package com.ssafy.fundyou.ui.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.ssafy.fundyou.ui.item_detail.model.RelatedItemModel
import org.apache.commons.lang3.builder.Diff

object RelatedItemDiffUtil : DiffUtil.ItemCallback<RelatedItemModel>(){
    override fun areItemsTheSame(oldItem: RelatedItemModel, newItem: RelatedItemModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RelatedItemModel, newItem: RelatedItemModel): Boolean {
        return oldItem == newItem
    }
}