package com.ssafy.fundyou.ui.funding_my_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemMyFundingStateListBinding
import com.ssafy.fundyou.ui.funding_my_detail.model.FundingItemStateUiModel

class FundingItemStateAdapter :
    ListAdapter<FundingItemStateUiModel, FundingItemStateAdapter.FundingItemStateHolder>(
        FundingItemStateDiffUtil
    ) {

    private lateinit var clickEvent : (Long) -> Unit

    inner class FundingItemStateHolder(private val binding: ItemMyFundingStateListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FundingItemStateUiModel) {
            binding.fundingStateItem = item
            binding.btnParticipate.setOnClickListener {
                clickEvent.invoke(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FundingItemStateHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_my_funding_state_list, parent, false
        )
    )

    override fun onBindViewHolder(holder: FundingItemStateHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun addButtonClickListener(event : (Long) -> Unit){
        this.clickEvent = event
    }

    object FundingItemStateDiffUtil : DiffUtil.ItemCallback<FundingItemStateUiModel>() {
        override fun areItemsTheSame(
            oldItem: FundingItemStateUiModel,
            newItem: FundingItemStateUiModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FundingItemStateUiModel,
            newItem: FundingItemStateUiModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}