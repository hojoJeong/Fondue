package com.ssafy.fundyou.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemMainCategoryBinding
import com.ssafy.fundyou.ui.home.model.MainCategoryModel
import com.ssafy.fundyou.ui.home.MainFragment

class MainCategoryAdapter : RecyclerView.Adapter<MainCategoryAdapter.MainCategoryViewHolder>() {
    private var categoryItemList = mutableListOf<MainCategoryModel>()
    private lateinit var activity: MainFragment

    inner class MainCategoryViewHolder(val binding: ItemMainCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MainCategoryModel) {
            binding.category = item
            binding.activity = activity
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainCategoryViewHolder {
        val view = DataBindingUtil.inflate<ItemMainCategoryBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_main_category,
            parent,
            false
        )
        return MainCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainCategoryViewHolder, position: Int) {
        holder.bind(categoryItemList[position])
    }

    override fun getItemCount(): Int = categoryItemList.size

    fun initCategoryItem(list: MutableList<MainCategoryModel>, context: MainFragment){
        categoryItemList.clear()
        categoryItemList.addAll(list)
        activity = context
    }
}