package com.ssafy.fundyou.ui.common.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.ssafy.fundyou.ui.funding_participate.model.FundingParticipateListModel

object InvitedFundingListModelDiffUtil : DiffUtil.ItemCallback<FundingParticipateListModel>(){
    override fun areItemsTheSame(
        oldItem: FundingParticipateListModel,
        newItem: FundingParticipateListModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: FundingParticipateListModel,
        newItem: FundingParticipateListModel
    ): Boolean {
        return oldItem == newItem
    }
}