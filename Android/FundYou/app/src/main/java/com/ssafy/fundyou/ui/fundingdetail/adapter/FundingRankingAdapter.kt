package com.ssafy.fundyou.ui.fundingdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemFundingRankingListBinding
import com.ssafy.fundyou.ui.adapter.diffutil.FundingItemModelDiffUtil
import com.ssafy.fundyou.ui.fundingdetail.model.FundingUserModel
import com.ssafy.fundyou.ui.myfunding.model.FundingItemModel

class FundingRankingAdapter :
    ListAdapter<FundingUserModel, FundingRankingAdapter.FundingRankingHolder>(FundingUserDiffUtil) {
    inner class FundingRankingHolder(private val binding: ItemFundingRankingListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FundingUserModel) {
            binding.fundingUserData = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FundingRankingHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_funding_ranking_list, parent, false
        )
    )

    override fun onBindViewHolder(holder: FundingRankingHolder, position: Int) {
        holder.bind(currentList[position])
    }

    object FundingUserDiffUtil : DiffUtil.ItemCallback<FundingUserModel>() {
        override fun areItemsTheSame(
            oldItem: FundingUserModel,
            newItem: FundingUserModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FundingUserModel,
            newItem: FundingUserModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}