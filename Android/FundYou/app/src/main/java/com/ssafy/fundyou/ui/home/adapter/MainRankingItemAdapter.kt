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

class MainRankingItemAdapter :
    ListAdapter<RankingItemModel, MainRankingItemAdapter.RankingItemHolder>(RankingDiffUtil()) {
    private lateinit var clickListener: (Long) -> Unit
    private lateinit var addLikeItem: (Long) -> Unit

    inner class RankingItemHolder(private val binding: ItemListProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RankingItemModel, position: Int) {
            with(binding) {
                rankingItem = item
                favoriteVisibility = true
                tvItemListProductRanking.visibility = View.VISIBLE
                tvItemListProductRanking.text = "${position + 1}위"
                root.setOnClickListener {
                    clickListener.invoke(item.id)
                }

                ivItemListProductFavorite.setOnClickListener {
                    addLikeItem.invoke(item.id)
                    when(item.isFavorite){
                        true -> {
                            ivItemListProductFavorite.setImageResource(R.drawable.ic_favorite_line)
                            item.isFavorite = false
                        }
                        false -> {
                            ivItemListProductFavorite.setImageResource(R.drawable.ic_favorite)
                            item.isFavorite = true
                        }
                    }
                }
            }
        }
    }


    fun addItemClickListener(itemId: (Long) -> Unit) {
        clickListener = itemId
    }

    fun addLikeBtnClickListener(itemId: (Long) -> Unit) {
        addLikeItem = itemId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingItemHolder {
        val view = DataBindingUtil.inflate<ItemListProductBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_list_product,
            parent,
            false
        )
        return RankingItemHolder(view)
    }

    override fun onBindViewHolder(holder: RankingItemHolder, position: Int) {
        holder.bind(getItem(position), position)
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