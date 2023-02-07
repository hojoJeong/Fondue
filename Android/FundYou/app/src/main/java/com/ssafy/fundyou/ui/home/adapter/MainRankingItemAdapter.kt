package com.ssafy.fundyou.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemListProductBinding
import com.ssafy.fundyou.ui.home.model.RankingItemModel

class MainRankingItemAdapter : ListAdapter<RankingItemModel, MainRankingItemAdapter.RankingItemHolder>(RankingDiffUtil()){

    class RankingItemHolder(private val binding: ItemListProductBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: RankingItemModel){
            with(binding){
                rankingItem = item
                favoriteVisibility = true
                tvItemListProductRanking.visibility = View.VISIBLE
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingItemHolder {
        val view = DataBindingUtil.inflate<ItemListProductBinding>(LayoutInflater.from(parent.context), R.layout.item_list_product, parent, false)
        return RankingItemHolder(view)
    }

    override fun onBindViewHolder(holder: RankingItemHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RankingDiffUtil : DiffUtil.ItemCallback<RankingItemModel>() {
        override fun areItemsTheSame(
            oldItem: RankingItemModel,
            newItem: RankingItemModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RankingItemModel,
            newItem: RankingItemModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}