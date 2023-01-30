package com.ssafy.fundyou.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemListProductBinding
import com.ssafy.fundyou.domain.model.ProductItemlModel

class ProductItemAdapter: ListAdapter<ProductItemlModel, ProductItemAdapter.ProductItemViewHolder>(
    ProductDiffUtil()
) {
    class ProductItemViewHolder(val binding: ItemListProductBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: ProductItemlModel, position: Int){
            when(item.isFavorite){
                true -> {
                    binding.ivItemListProductFavorite.setImageResource(R.drawable.bg_favorite)
                }
                false -> {
                    binding.ivItemListProductFavorite.setImageResource(R.drawable.bg_favorite_line)
                }
            }

            with(binding){
                tvItemListProductRanking.text = "${position+1}위"
                this.product = item

                if(!item.isAr){
                    binding.btnItemListProductAr.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val view = DataBindingUtil.inflate<ItemListProductBinding>(LayoutInflater.from(parent.context),
            R.layout.item_list_product, parent, false)
        return ProductItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    class ProductDiffUtil : DiffUtil.ItemCallback<ProductItemlModel>(){
        override fun areItemsTheSame(oldItem: ProductItemlModel, newItem: ProductItemlModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductItemlModel, newItem: ProductItemlModel): Boolean {
            return oldItem == newItem
        }
    }
}