package com.ssafy.fundyou.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemMainRandomBinding
import com.ssafy.fundyou.domain.model.ProductItemModel

class MainRandomItemAdapter : ListAdapter<ProductItemModel, MainRandomItemAdapter.MainRandomItemViewHolder>(
    ProductDiffUtil()
) {

    class MainRandomItemViewHolder(val binding: ItemMainRandomBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: ProductItemModel){
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

    class ProductDiffUtil : DiffUtil.ItemCallback<ProductItemModel>(){
        override fun areItemsTheSame(oldItem: ProductItemModel, newItem: ProductItemModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductItemModel, newItem: ProductItemModel): Boolean {
            return oldItem == newItem
        }
    }
}