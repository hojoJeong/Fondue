package com.ssafy.fundyou.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemSearchResultListBinding
import com.ssafy.fundyou.domain.model.item.ProductItemModel
import com.ssafy.fundyou.ui.adapter.diffutil.ProductListDiffUtil
import com.ssafy.fundyou.ui.search.model.SearchItemModel
import org.apache.commons.lang3.builder.Diff

class SearchResultAdapter : ListAdapter<SearchItemModel, SearchResultAdapter.SearchResultHolder>(SearchItemDiffUtil) {

    private lateinit var clickEvent : (Long) -> Unit

    inner class SearchResultHolder(private val binding : ItemSearchResultListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : SearchItemModel){
            binding.item = item

            binding.root.setOnClickListener {
                clickEvent.invoke(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultHolder =
        SearchResultHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_search_result_list, parent, false))

    override fun onBindViewHolder(holder: SearchResultHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun initItemClickEvent(event : (Long) -> Unit) {
        this.clickEvent = event
    }

    object SearchItemDiffUtil : DiffUtil.ItemCallback<SearchItemModel>(){
        override fun areItemsTheSame(oldItem: SearchItemModel, newItem: SearchItemModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SearchItemModel,
            newItem: SearchItemModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}