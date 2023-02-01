package com.ssafy.fundyou.ui.myfunding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemMyFundingListBinding
import com.ssafy.fundyou.ui.myfunding.model.FundingItemModel

class MyFundingListAdapter : ListAdapter<FundingItemModel, MyFundingListAdapter.MyFundingListHolder>(FundingItemModelDiffUtil){

    private lateinit var clickEvent : (Int) -> Unit

    inner class MyFundingListHolder(private val binding: ItemMyFundingListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : FundingItemModel){
            binding.item = item
            binding.btnFunding.setOnClickListener {
                clickEvent.invoke(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyFundingListHolder(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_my_funding_list, parent, false)
    )

    override fun onBindViewHolder(holder: MyFundingListHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun addClickEvent(event : (Int) -> Unit){
        this.clickEvent = event
    }

    object FundingItemModelDiffUtil : DiffUtil.ItemCallback<FundingItemModel>(){
        override fun areItemsTheSame(
            oldItem: FundingItemModel,
            newItem: FundingItemModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FundingItemModel,
            newItem: FundingItemModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}