package com.ssafy.fundyou.ui.common.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.ssafy.fundyou.ui.funding_invited_list.model.FundingParticipateListUiModel

object InvitedFundingListModelDiffUtil : DiffUtil.ItemCallback<FundingParticipateListUiModel>(){
    override fun areItemsTheSame(
        oldItem: FundingParticipateListUiModel,
        newItem: FundingParticipateListUiModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: FundingParticipateListUiModel,
        newItem: FundingParticipateListUiModel
    ): Boolean {
        return oldItem == newItem
    }
}