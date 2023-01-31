package com.ssafy.fundyou.ui.itemdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemItemDetailInfoListBinding
import com.ssafy.fundyou.ui.itemdetail.model.ItemDetailInfoModel

class ItemDetailInfoAdapter : ListAdapter<ItemDetailInfoModel, ItemDetailInfoAdapter.ItemDetailInfoHolder>(ItemDetailInfoDiffUtil){
    class ItemDetailInfoHolder(private val binding : ItemItemDetailInfoListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : ItemDetailInfoModel){
            binding.itemDetailInfo = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemDetailInfoHolder(DataBindingUtil.inflate(
        LayoutInflater.from(parent.context), R.layout.item_item_detail_info_list,parent, false))

    override fun onBindViewHolder(holder: ItemDetailInfoHolder, position: Int) {
        holder.bind(currentList[position])
    }

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
}