package com.ssafy.fundyou.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.databinding.ItemPopularKeywordListBinding
import com.ssafy.fundyou.domain.model.search.PopularKeywordEntity

class PopularSearchKeywordAdapter :
    ListAdapter<PopularKeywordEntity, PopularSearchKeywordAdapter.MainPopularSearchViewHolder>(
        SearchPopularKeywordDiffUtil
    ) {

    private lateinit var clickEvent : (String) -> Unit

    inner class MainPopularSearchViewHolder(val binding: ItemPopularKeywordListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PopularKeywordEntity, position: Int) {
            binding.tvRank.text = "${position + 1}"
            binding.tvPopularKeyword.text = item.keyword

            binding.root.setOnClickListener {
                clickEvent.invoke(item.keyword)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPopularSearchViewHolder {
        val view = ItemPopularKeywordListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainPopularSearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainPopularSearchViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    object SearchPopularKeywordDiffUtil : DiffUtil.ItemCallback<PopularKeywordEntity>() {
        override fun areItemsTheSame(
            oldItem: PopularKeywordEntity,
            newItem: PopularKeywordEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PopularKeywordEntity,
            newItem: PopularKeywordEntity
        ): Boolean {
            return oldItem == newItem
        }
    }

    fun addItemClickEvent(event : (String) -> Unit) {
        this.clickEvent = event
    }
}