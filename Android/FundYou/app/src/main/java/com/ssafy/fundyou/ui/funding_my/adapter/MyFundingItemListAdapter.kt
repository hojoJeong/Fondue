package com.ssafy.fundyou.ui.funding_my.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemMyFundingItemListBinding
import com.ssafy.fundyou.ui.adapter.diffutil.MyFundingItemDiffUtil
import com.ssafy.fundyou.ui.funding_my.model.MyFundingItemInfoUiModel

class MyFundingItemListAdapter :
    ListAdapter<MyFundingItemInfoUiModel, MyFundingItemListAdapter.MyFundingListHolder>(
        MyFundingItemDiffUtil
    ) {

    private lateinit var clickEvent: (Long) -> Unit

    inner class MyFundingListHolder(private val binding: ItemMyFundingItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyFundingItemInfoUiModel) {
            binding.fundingItem = item
            binding.btnFunding.setOnClickListener {
                clickEvent.invoke(item.fundingItemId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyFundingListHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_my_funding_item_list,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MyFundingListHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun addClickEvent(event: (Long) -> Unit) {
        this.clickEvent = event
    }
}