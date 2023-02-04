package com.ssafy.fundyou.ui.funding_participate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentInvitedFondueBinding
import com.ssafy.fundyou.databinding.ItemMyFundingListBinding
import com.ssafy.fundyou.ui.adapter.diffutil.InvitedFundingModelDiffUtil
import com.ssafy.fundyou.ui.funding_participate.model.InvitedFundingModel

class InvitedFondueItemAdapter : ListAdapter<InvitedFundingModel, InvitedFondueItemAdapter.InvitedFondueItemHolder>(InvitedFundingModelDiffUtil){

    class InvitedFondueItemHolder(private val binding: ItemMyFundingListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : InvitedFundingModel){
            binding.invitedFunding = item
            binding.item = item.fundingItemModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvitedFondueItemHolder {
        val view = DataBindingUtil.inflate<ItemMyFundingListBinding>(LayoutInflater.from(parent.context), R.layout.item_my_funding_list, parent, false)
        return InvitedFondueItemHolder(view)
    }

    override fun onBindViewHolder(holder: InvitedFondueItemHolder, position: Int) {
        holder.bind(getItem(position))
    }

}