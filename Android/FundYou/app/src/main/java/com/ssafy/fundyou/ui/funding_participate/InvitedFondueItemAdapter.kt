package com.ssafy.fundyou.ui.funding_participate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemInvitedFondueBinding
import com.ssafy.fundyou.ui.adapter.diffutil.FundingItemModelDiffUtil
import com.ssafy.fundyou.ui.adapter.diffutil.InvitedFundingDiffUtil
import com.ssafy.fundyou.ui.funding_participate.model.InvitedFundingModel
import com.ssafy.fundyou.ui.myfunding.model.FundingItemModel

class InvitedFondueItemAdapter : ListAdapter<InvitedFundingModel, InvitedFondueItemAdapter.InvitedFondueItemHolder>(InvitedFundingDiffUtil){
    inner class InvitedFondueItemHolder(private val binding: ItemInvitedFondueBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: InvitedFundingModel){
            binding.fundingItem = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvitedFondueItemHolder {
        val view = DataBindingUtil.inflate<ItemInvitedFondueBinding>(LayoutInflater.from(parent.context), R.layout.item_invited_fondue, parent, false)
        return InvitedFondueItemHolder(view)
    }

    override fun onBindViewHolder(holder: InvitedFondueItemHolder, position: Int) {
        holder.bind(getItem(position))
    }
}