package com.ssafy.fundyou.ui.wishlist

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemListProductBinding

class WishListAdapter : ListAdapter<WishListModel, WishListAdapter.WishListItemHolder>(WishListDiffUtil()) {
    private lateinit var wishListItemId: (Long) -> Unit
    inner class WishListItemHolder(private val binding: ItemListProductBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: WishListModel){
            with(binding){
                wishlistItem = item
                favoriteVisibility = false
                Log.d(TAG, "bind: $item")
                btnItemListCancel.setOnClickListener {
                    wishListItemId.invoke(item.itemId)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListItemHolder {
        val view = DataBindingUtil.inflate<ItemListProductBinding>(LayoutInflater.from(parent.context), R.layout.item_list_product, parent, false)
        return WishListItemHolder(view)
    }

    override fun onBindViewHolder(holder: WishListItemHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class WishListDiffUtil : DiffUtil.ItemCallback<WishListModel>() {
        override fun areItemsTheSame(
            oldItem: WishListModel,
            newItem: WishListModel
        ): Boolean {
            return oldItem.itemId == newItem.itemId
        }

        override fun areContentsTheSame(
            oldItem: WishListModel,
            newItem: WishListModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    fun deleteItemBtnClickListener(itemId: (Long) -> Unit) {
        wishListItemId = itemId
    }

}