package com.ssafy.fundyou.ui.funding_my.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemFundingMessageBinding
import com.ssafy.fundyou.ui.funding_my.model.FundingParticipateModel


class FundingParticipateAdapter :
    ListAdapter<FundingParticipateModel, FundingParticipateAdapter.FundingParticipateHolder>(
        FundingParticipateDiffUtil
    ) {
    inner class FundingParticipateHolder(private val binding: ItemFundingMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FundingParticipateModel) {
            binding.message = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FundingParticipateHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_funding_message,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: FundingParticipateHolder, position: Int) {
        holder.bind(currentList[position])
    }

    object FundingParticipateDiffUtil : DiffUtil.ItemCallback<FundingParticipateModel>() {
        override fun areItemsTheSame(
            oldItem: FundingParticipateModel,
            newItem: FundingParticipateModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FundingParticipateModel,
            newItem: FundingParticipateModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}