package com.ssafy.fundyou

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.databinding.ItemMainPopularSearchBinding
import com.ssafy.fundyou.domain.model.ProductItemlModel

class MainPopularSearchAdapter: ListAdapter<String, MainPopularSearchAdapter.MainPopularSearchViewHolder>(ProductDiffUtil()) {

    class MainPopularSearchViewHolder(val binding: ItemMainPopularSearchBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(term: String, position: Int){
            binding.tvMainPopularRank.text = "${position + 1}"
            binding.tvMainPopularTerm.text = term
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPopularSearchViewHolder {
        val view = ItemMainPopularSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainPopularSearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainPopularSearchViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    class ProductDiffUtil : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}