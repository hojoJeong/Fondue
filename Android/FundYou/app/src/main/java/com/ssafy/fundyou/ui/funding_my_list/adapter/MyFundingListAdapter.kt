package com.ssafy.fundyou.ui.funding_my_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemMyFundingItemListBinding
import com.ssafy.fundyou.databinding.ItemMyFundingListBinding
import com.ssafy.fundyou.ui.funding_my_list.model.MyFundingListModel

class MyFundingListAdapter : ListAdapter<MyFundingListModel, MyFundingListAdapter.MyFundingListHolder>(MyFundingListDiffUtil){

    private lateinit var clickEvent : (Long) -> Unit

    class MyFundingListHolder(private val binding : ItemMyFundingListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(funding : MyFundingListModel){
            binding.fundingItem = funding
            binding.root.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyFundingListHolder(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_my_funding_list, parent, false)
    )

    override fun onBindViewHolder(holder: MyFundingListHolder, position: Int) {
        holder.bind(currentList[position])
    }

    object MyFundingListDiffUtil : DiffUtil.ItemCallback<MyFundingListModel>(){
        override fun areItemsTheSame(
            oldItem: MyFundingListModel,
            newItem: MyFundingListModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MyFundingListModel,
            newItem: MyFundingListModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    fun addClickEvent(event : (Long) -> Unit){
        clickEvent = event
    }
}