package com.ssafy.fundyou.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemMainCategoryBinding
import com.ssafy.fundyou.ui.home.model.MainCategoryModel
import com.ssafy.fundyou.ui.home.MainFragment

class MainCategoryAdapter(private val categoryType: (String) -> Unit) : RecyclerView.Adapter<MainCategoryAdapter.MainCategoryViewHolder>() {
    private var categoryItemList = mutableListOf<MainCategoryModel>()


    inner class MainCategoryViewHolder(val binding: ItemMainCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MainCategoryModel) {
            binding.category = item
            binding.cstlItemMainCategory.setOnClickListener {
                categoryType.invoke(binding.tvMainItemCategory.text.toString())
            }
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

    fun initCategoryItem(list: MutableList<MainCategoryModel>){
        categoryItemList.clear()
        categoryItemList.addAll(list)
    }
}