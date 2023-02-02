package com.ssafy.fundyou.ui.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.ssafy.fundyou.ui.funding_participate.model.InvitedFundingModel

object InvitedFundingDiffUtil : DiffUtil.ItemCallback<InvitedFundingModel>(){
    override fun areItemsTheSame(
        oldItem: InvitedFundingModel,
        newItem: InvitedFundingModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: InvitedFundingModel,
        newItem: InvitedFundingModel
    ): Boolean {
        return oldItem == newItem
    }
}