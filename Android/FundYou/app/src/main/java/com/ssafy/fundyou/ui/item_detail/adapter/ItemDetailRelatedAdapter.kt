package com.ssafy.fundyou.ui.item_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemRelatedItemListBinding
import com.ssafy.fundyou.domain.model.ProductItemModel
import com.ssafy.fundyou.ui.adapter.diffutil.ProductListDiffUtil

class ItemDetailRelatedAdapter : ListAdapter<ProductItemModel, ItemDetailRelatedAdapter.ItemDetailRelatedHolder>(ProductListDiffUtil){

    inner class ItemDetailRelatedHolder(private val binding : ItemRelatedItemListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : ProductItemModel){
            binding.product = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemDetailRelatedHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_related_item_list, parent, false))

    override fun onBindViewHolder(holder: ItemDetailRelatedHolder, position: Int){
        holder.bind(currentList[position])
    }
}