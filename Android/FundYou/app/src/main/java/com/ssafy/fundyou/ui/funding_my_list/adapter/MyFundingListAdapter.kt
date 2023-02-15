package com.ssafy.fundyou.ui.funding_my_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemMyFundingListBinding
import com.ssafy.fundyou.ui.funding_my_list.model.MyFundingListUiModel

class MyFundingListAdapter : ListAdapter<MyFundingListUiModel, MyFundingListAdapter.MyFundingListHolder>(MyFundingListDiffUtil){

    private lateinit var clickEvent : (Long) -> Unit

    inner class MyFundingListHolder(private val binding : ItemMyFundingListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(funding : MyFundingListUiModel){
            binding.fundingItem = funding

            val imgAdapter = MyFundingListImgAdapter()
            imgAdapter.addImageList(funding.imgList)
            binding.rvFundingItemImg.adapter = imgAdapter

            binding.root.setOnClickListener {
                clickEvent.invoke(funding.id)
            }

            binding.rvFundingItemImg.setOnClickListener{
                clickEvent.invoke(funding.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyFundingListHolder(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_my_funding_list, parent, false)
    )

    override fun onBindViewHolder(holder: MyFundingListHolder, position: Int) {
        holder.bind(currentList[position])
    }

    object MyFundingListDiffUtil : DiffUtil.ItemCallback<MyFundingListUiModel>(){
        override fun areItemsTheSame(
            oldItem: MyFundingListUiModel,
            newItem: MyFundingListUiModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MyFundingListUiModel,
            newItem: MyFundingListUiModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    fun addClickEvent(event : (Long) -> Unit){
        clickEvent = event
    }
}