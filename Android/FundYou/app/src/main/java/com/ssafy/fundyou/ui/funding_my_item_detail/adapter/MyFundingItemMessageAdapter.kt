package com.ssafy.fundyou.ui.funding_my_item_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemFundingMessageBinding
import com.ssafy.fundyou.ui.funding_my_item_detail.model.MyFundingParticipateMessageModel

class MyFundingItemMessageAdapter :
    ListAdapter<MyFundingParticipateMessageModel, MyFundingItemMessageAdapter.MyFundingItemMessageHolder>(
        FundingParticipateMessageDiffUtil
    ) {


    inner class MyFundingItemMessageHolder(private val binding: ItemFundingMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyFundingParticipateMessageModel) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyFundingItemMessageHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_funding_message,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MyFundingItemMessageHolder, position: Int) {
        holder.bind(currentList[position])
    }

    object FundingParticipateMessageDiffUtil :
        DiffUtil.ItemCallback<MyFundingParticipateMessageModel>() {
        override fun areItemsTheSame(
            oldItem: MyFundingParticipateMessageModel,
            newItem: MyFundingParticipateMessageModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MyFundingParticipateMessageModel,
            newItem: MyFundingParticipateMessageModel
        ): Boolean {
            return oldItem == newItem
        }
    }


}