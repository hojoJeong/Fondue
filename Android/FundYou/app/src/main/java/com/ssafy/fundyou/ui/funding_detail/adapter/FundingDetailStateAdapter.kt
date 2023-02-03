package com.ssafy.fundyou.ui.funding_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemMyFundingStateListBinding
import com.ssafy.fundyou.ui.adapter.diffutil.FundingItemModelDiffUtil
import com.ssafy.fundyou.ui.funding_my.model.FundingItemModel

class FundingDetailStateAdapter :
    ListAdapter<FundingItemModel, FundingDetailStateAdapter.FundingDetailStateHolder>(
        FundingItemModelDiffUtil
    ) {

    private lateinit var clickParticipateButtonEvent: (Int) -> Unit

    inner class FundingDetailStateHolder(private val binding: ItemMyFundingStateListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FundingItemModel) {
            binding.fundingItem = item
            binding.btnParticipate.setOnClickListener{
                clickParticipateButtonEvent.invoke(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FundingDetailStateHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_my_funding_state_list, parent, false))

    override fun onBindViewHolder(holder: FundingDetailStateAdapter.FundingDetailStateHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun addParticipateButtonEvent(event: (Int) -> Unit) {
        clickParticipateButtonEvent = event
    }
}