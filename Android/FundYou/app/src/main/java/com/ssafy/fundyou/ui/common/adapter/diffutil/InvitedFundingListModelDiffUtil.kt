package com.ssafy.fundyou.ui.common.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.ssafy.fundyou.ui.funding_participate.model.InvitedFundingListModel

object InvitedFundingListModelDiffUtil : DiffUtil.ItemCallback<InvitedFundingListModel>(){
    override fun areItemsTheSame(
        oldItem: InvitedFundingListModel,
        newItem: InvitedFundingListModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: InvitedFundingListModel,
        newItem: InvitedFundingListModel
    ): Boolean {
        return oldItem == newItem
    }
}