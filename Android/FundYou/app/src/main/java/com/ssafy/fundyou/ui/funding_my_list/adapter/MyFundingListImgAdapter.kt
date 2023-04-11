package com.ssafy.fundyou.ui.funding_my_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.databinding.ItemMyFundingListItemImgBinding

class MyFundingListImgAdapter : RecyclerView.Adapter<MyFundingListImgAdapter.MyFundingListImgHolder>(){

    private val imgList = mutableListOf<String>()

    class MyFundingListImgHolder(private val binding : ItemMyFundingListItemImgBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imgSrc: String) {
            binding.imgSrc = imgSrc
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyFundingListImgHolder(
        ItemMyFundingListItemImgBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyFundingListImgHolder, position: Int) {
        holder.bind(imgList[position])
    }

    override fun getItemCount()= imgList.size

    fun addImageList(list : List<String>){
        imgList.addAll(list)
    }

}