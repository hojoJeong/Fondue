package com.ssafy.fundyou.ui.item_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemItemDetailInfoListBinding
import com.ssafy.fundyou.ui.adapter.diffutil.ItemDetailInfoDiffUtil
import com.ssafy.fundyou.domain.model.item.ItemDescriptionModel

class ItemDetailInfoAdapter : ListAdapter<ItemDescriptionModel, ItemDetailInfoAdapter.ItemDetailInfoHolder>(
    ItemDetailInfoDiffUtil
){
    class ItemDetailInfoHolder(private val binding : ItemItemDetailInfoListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : ItemDescriptionModel){
            binding.itemDetailInfo = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemDetailInfoHolder(DataBindingUtil.inflate(
        LayoutInflater.from(parent.context), R.layout.item_item_detail_info_list,parent, false))

    override fun onBindViewHolder(holder: ItemDetailInfoHolder, position: Int) {
        holder.bind(currentList[position])
    }
}