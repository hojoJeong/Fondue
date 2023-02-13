package com.ssafy.fundyou.ui.common.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.ssafy.fundyou.domain.model.item.ProductItemModel

object ProductListDiffUtil : DiffUtil.ItemCallback<ProductItemModel>(){
    override fun areItemsTheSame(
        oldItem: ProductItemModel,
        newItem: ProductItemModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ProductItemModel,
        newItem: ProductItemModel
    ): Boolean {
        return oldItem == newItem
    }
}