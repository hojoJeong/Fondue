package com.ssafy.fundyou.ui.funding_participate.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemFundingParticipateItemListBinding
import com.ssafy.fundyou.ui.funding_participate.model.FundingParticipateItemUiModel

class FundingParticipateItemAdapter : ListAdapter<FundingParticipateItemUiModel, FundingParticipateItemAdapter.FundingParticipateItemHolder>(FundingParticipateItemDiffUtil){

    private lateinit var fundingClickEvent : (Long) -> Unit
    private lateinit var itemClickEvent : (Long) -> Unit

    inner class FundingParticipateItemHolder(private val binding : ItemFundingParticipateItemListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : FundingParticipateItemUiModel){
            binding.fundingItem = item
            
            binding.btnFunding.setOnClickListener {
                fundingClickEvent.invoke(item.id)
            }
            binding.cstlFundingItem.setOnClickListener {
                itemClickEvent.invoke(item.id)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FundingParticipateItemHolder {
        return FundingParticipateItemHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_funding_participate_item_list, parent, false))
    }

    override fun onBindViewHolder(holder: FundingParticipateItemHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun addFundingButtonEvent(event : (Long) -> Unit){
        fundingClickEvent = event
    }

    fun addItemClickButtonEvent(event : (Long) -> Unit){
        itemClickEvent = event
    }

    object FundingParticipateItemDiffUtil : DiffUtil.ItemCallback<FundingParticipateItemUiModel>(){
        override fun areItemsTheSame(
            oldItem: FundingParticipateItemUiModel,
            newItem: FundingParticipateItemUiModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FundingParticipateItemUiModel,
            newItem: FundingParticipateItemUiModel
        ): Boolean {
            return oldItem == newItem
        }
    }

}