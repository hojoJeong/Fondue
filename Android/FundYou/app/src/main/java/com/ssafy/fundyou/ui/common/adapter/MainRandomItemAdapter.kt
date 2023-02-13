package com.ssafy.fundyou.ui.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemMainRandomBinding
import com.ssafy.fundyou.ui.home.model.RandomItemModel

class MainRandomItemAdapter : ListAdapter<RandomItemModel, MainRandomItemAdapter.MainRandomItemViewHolder>(
    RandomItemDiffUtil()
) {

    class MainRandomItemViewHolder(val binding: ItemMainRandomBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: RandomItemModel){
            binding.product = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRandomItemViewHolder {
        val view = DataBindingUtil.inflate<ItemMainRandomBinding>(LayoutInflater.from(parent.context),
            R.layout.item_main_random, parent, false)
        return MainRandomItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainRandomItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RandomItemDiffUtil : DiffUtil.ItemCallback<RandomItemModel>(){
        override fun areItemsTheSame(oldItem: RandomItemModel, newItem: RandomItemModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RandomItemModel, newItem: RandomItemModel): Boolean {
            return oldItem == newItem
        }
    }
}