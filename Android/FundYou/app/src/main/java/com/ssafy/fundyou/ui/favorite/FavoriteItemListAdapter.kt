package com.ssafy.fundyou.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemListProductBinding

class FavoriteItemListAdapter : ListAdapter<FavoriteItemModel, FavoriteItemListAdapter.FavoriteItemHolder>(FavoriteDiffUtil()){

    class FavoriteItemHolder(private val binding: ItemListProductBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: FavoriteItemModel){
            with(binding){
                favoriteItem = item
                favoriteVisibility = false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteItemHolder {
        val view = DataBindingUtil.inflate<ItemListProductBinding>(LayoutInflater.from(parent.context), R.layout.item_list_product, parent, false)
        return FavoriteItemHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteItemHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class FavoriteDiffUtil : DiffUtil.ItemCallback<FavoriteItemModel>() {
        override fun areItemsTheSame(
            oldItem: FavoriteItemModel,
            newItem: FavoriteItemModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FavoriteItemModel,
            newItem: FavoriteItemModel
        ): Boolean {
            return oldItem == newItem
        }
    }

}