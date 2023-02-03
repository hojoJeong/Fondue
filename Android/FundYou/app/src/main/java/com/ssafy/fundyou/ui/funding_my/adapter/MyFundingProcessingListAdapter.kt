package com.ssafy.fundyou.ui.funding_my.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemMyFundingListBinding
import com.ssafy.fundyou.ui.adapter.diffutil.FundingItemModelDiffUtil
import com.ssafy.fundyou.ui.funding_my.model.FundingItemModel

class MyFundingProcessingListAdapter : ListAdapter<FundingItemModel, MyFundingProcessingListAdapter.MyFundingListHolder>(
    FundingItemModelDiffUtil
){

    private lateinit var clickEvent : (Int) -> Unit

    inner class MyFundingListHolder(private val binding: ItemMyFundingListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : FundingItemModel){
            binding.item = item
            binding.btnFunding.setOnClickListener {
                clickEvent.invoke(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyFundingListHolder(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_my_funding_list, parent, false)
    )

    override fun onBindViewHolder(holder: MyFundingListHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun addClickEvent(event : (Int) -> Unit){
        this.clickEvent = event
    }
}