package com.ssafy.fundyou.ui.like

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemListProductBinding

class LikeItemListAdapter :
    ListAdapter<LikeItemModel, LikeItemListAdapter.FavoriteItemHolder>(FavoriteDiffUtil()) {
    private lateinit var itemClickEvent: (Long) -> Unit
    private lateinit var removeBtnClickEvent: (Long) -> Unit

    inner class FavoriteItemHolder(private val binding: ItemListProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LikeItemModel) {
            with(binding) {
                favoriteItem = item
                favoriteVisibility = false
                root.setOnClickListener {
                    itemClickEvent.invoke(item.id)
                }
                btnItemListCancel.setOnClickListener {
                    removeBtnClickEvent.invoke(item.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteItemHolder {
        val view = DataBindingUtil.inflate<ItemListProductBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_list_product,
            parent,
            false
        )
        return FavoriteItemHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteItemHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun addItemClickLIstener(id: (Long) -> Unit) {
        itemClickEvent = id
    }

    fun addRemoveItemBtnClickListener(id: (Long) -> Unit) {
        removeBtnClickEvent = id
    }


    class FavoriteDiffUtil : DiffUtil.ItemCallback<LikeItemModel>() {
        override fun areItemsTheSame(
            oldItem: LikeItemModel,
            newItem: LikeItemModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: LikeItemModel,
            newItem: LikeItemModel
        ): Boolean {
            return oldItem == newItem
        }
    }

}