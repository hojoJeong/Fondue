package com.ssafy.fundyou.ui.item_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemItemImgListBinding

class ItemDetailImgAdapter : RecyclerView.Adapter<ItemDetailImgAdapter.ItemDetailImgHolder>() {

    private val itemImgList = mutableListOf<ItemImgModel>()

    class ItemDetailImgHolder(private val binding : ItemItemImgListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : ItemImgModel){
           binding.itemImg = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDetailImgHolder =
        ItemDetailImgHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_item_img_list, parent, false))

    override fun onBindViewHolder(holder: ItemDetailImgHolder, position: Int) {
        holder.bind(itemImgList[position % itemImgList.size])
    }

    override fun getItemCount() = Int.MAX_VALUE

    fun addItemImgList(list : List<ItemImgModel>){
        itemImgList.clear()
        itemImgList.addAll(list)
    }

}