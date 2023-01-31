package com.ssafy.fundyou.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemSearchResultListBinding
import com.ssafy.fundyou.domain.model.ProductItemModel

class SearchResultAdapter : ListAdapter<ProductItemModel, SearchResultAdapter.SearchResultHolder>(SearchResultDiffUtil) {

    private lateinit var clickEvent : (Int) -> Unit

    inner class SearchResultHolder(private val binding : ItemSearchResultListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : ProductItemModel){
            binding.product = item

            binding.root.setOnClickListener {
                clickEvent.invoke(item.id.toInt())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultHolder =
        SearchResultHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_search_result_list, parent, false))

    override fun onBindViewHolder(holder: SearchResultHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun initEvent(event : (Int) -> Unit) {
        this.clickEvent = event
    }

    object SearchResultDiffUtil : DiffUtil.ItemCallback<ProductItemModel>(){
        override fun areItemsTheSame(
            oldItem: ProductItemModel,
            newItem: ProductItemModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProductItemModel,
            newItem: ProductItemModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}