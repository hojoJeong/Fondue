package com.ssafy.fundyou.ui.item_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemListProductBinding
import com.ssafy.fundyou.ui.item_list.model.ItemListModel

class ItemListAdapter : ListAdapter<ItemListModel, ItemListAdapter.ItemListViewHolder>(ItemListDiffUtil()){
    private lateinit var itemClickEvent: (Long) -> Unit
    private lateinit var addLikeItemEvent : (Long) -> Unit
    inner class ItemListViewHolder(private val binding: ItemListProductBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ItemListModel){
            with(binding){
                product = item
                favoriteVisibility = true
                ivItemListProductFavorite.setOnClickListener {
                    addLikeItemEvent.invoke(item.id)
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
                root.setOnClickListener {
                    itemClickEvent.invoke(item.id)
                }
            }
        }
    }

    //아이템 갱신 시 깜빡임 현상 방지
    override fun getItemId(position: Int): Long {
        return getItem(position).id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        val view = DataBindingUtil.inflate<ItemListProductBinding>(LayoutInflater.from(parent.context), R.layout.item_list_product, parent, false)
        return ItemListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun addLikeItemBtnClickListener(id: (Long) -> Unit){
        addLikeItemEvent = id
    }

    fun addItemClickListener(id: (Long) -> Unit) {
        itemClickEvent = id
    }


    class ItemListDiffUtil : DiffUtil.ItemCallback<ItemListModel>() {
        override fun areItemsTheSame(
            oldItem: ItemListModel,
            newItem: ItemListModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ItemListModel,
            newItem: ItemListModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}