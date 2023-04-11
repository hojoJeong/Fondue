package com.ssafy.fundyou.ui.funding_my.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemEndFundingListBinding
import com.ssafy.fundyou.ui.adapter.diffutil.FundingItemModelDiffUtil
import com.ssafy.fundyou.ui.funding_my.model.FundingItemModel

class MyFundingEndListAdapter :
    ListAdapter<FundingItemModel, MyFundingEndListAdapter.MyFundingEndListHolder>(
        FundingItemModelDiffUtil
    ) {

    private lateinit var clickEvent: (Int) -> Unit

    inner class MyFundingEndListHolder(private val binding: ItemEndFundingListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FundingItemModel) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyFundingEndListHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_end_funding_list, parent, false
        )
    )

    override fun onBindViewHolder(holder: MyFundingEndListHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun addClickEvent(event: (Int) -> Unit) {
        this.clickEvent = event
    }
}