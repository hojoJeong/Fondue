package com.ssafy.fundyou.ui.funding_my_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemFundingParticipateListBinding
import com.ssafy.fundyou.ui.funding_my_detail.model.FundingParticipateUserUiModel

class FundingParticipateUserAdapter : ListAdapter<FundingParticipateUserUiModel, FundingParticipateUserAdapter.FundingParticipateUserHolder>(
    FundingParticipateUserDiffUtil
) {

    inner class FundingParticipateUserHolder(private val binding : ItemFundingParticipateListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : FundingParticipateUserUiModel){
            binding.participateUserModel = item
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = FundingParticipateUserHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_funding_participate_list, parent, false))

    override fun onBindViewHolder(holder: FundingParticipateUserHolder, position: Int) {
        holder.bind(currentList[position])
    }

    object FundingParticipateUserDiffUtil : DiffUtil.ItemCallback<FundingParticipateUserUiModel>(){
        override fun areItemsTheSame(
            oldItem: FundingParticipateUserUiModel,
            newItem: FundingParticipateUserUiModel
        ): Boolean {
            return oldItem.participateUserName == newItem.participateUserName
        }

        override fun areContentsTheSame(
            oldItem: FundingParticipateUserUiModel,
            newItem: FundingParticipateUserUiModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}