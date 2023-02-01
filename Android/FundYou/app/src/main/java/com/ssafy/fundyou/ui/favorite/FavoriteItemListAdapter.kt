package com.ssafy.fundyou.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemListProductBinding
import com.ssafy.fundyou.domain.model.ProductItemModel
import com.ssafy.fundyou.ui.adapter.diffutil.ProductListDiffUtil

class FavoriteItemListAdapter : ListAdapter<ProductItemModel, FavoriteItemListAdapter.FavoriteItemHolder>(ProductListDiffUtil){

    class FavoriteItemHolder(private val binding : ItemListProductBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ProductItemModel){
            binding.ivItemListProductFavorite.visibility = View.GONE
            binding.product = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteItemHolder {
        val view = DataBindingUtil.inflate<ItemListProductBinding>(LayoutInflater.from(parent.context), R.layout.item_list_product, parent, false)
        return FavoriteItemHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteItemHolder, position: Int) {
        holder.bind(getItem(position))
    }
}