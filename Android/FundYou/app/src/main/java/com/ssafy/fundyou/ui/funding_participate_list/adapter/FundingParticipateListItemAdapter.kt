package com.ssafy.fundyou.ui.funding_invited_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemInvitedFondueListBinding
import com.ssafy.fundyou.ui.common.adapter.diffutil.InvitedFundingListModelDiffUtil
import com.ssafy.fundyou.ui.funding_invited_list.model.FundingParticipateListUiModel

class FundingParticipateListItemAdapter : ListAdapter<FundingParticipateListUiModel, FundingParticipateListItemAdapter.InvitedFondueItemHolder>(
    InvitedFundingListModelDiffUtil
){
    private lateinit var clickListener : (Long) -> Unit
    inner class InvitedFondueItemHolder(private val binding: ItemInvitedFondueListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: FundingParticipateListUiModel){
            binding.fundingItem = item
            binding.cdvItemInvitedFondue.setOnClickListener {
                clickListener.invoke(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvitedFondueItemHolder {
        val view = DataBindingUtil.inflate<ItemInvitedFondueListBinding>(LayoutInflater.from(parent.context), R.layout.item_invited_fondue_list, parent, false)
        return InvitedFondueItemHolder(view)
    }

    override fun onBindViewHolder(holder: InvitedFondueItemHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun fundingItemClickListener(fundingId: (Long) -> Unit){
        clickListener = fundingId
    }
}