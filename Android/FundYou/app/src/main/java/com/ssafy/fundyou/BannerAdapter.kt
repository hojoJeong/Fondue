package com.ssafy.fundyou

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.fundyou.databinding.ItemMainBannerBinding

class BannerAdapter : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {
    private val bannerItems = mutableListOf<Int>()

    class BannerViewHolder(private val binding: ItemMainBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(image: Int) {
            binding.ivMainBanner.setImageResource(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val imageBinding =
            ItemMainBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(imageBinding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.onBind(bannerItems[position % bannerItems.size])
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    fun addAllItems(list : List<Int>){
        bannerItems.clear()
        bannerItems.addAll(list)
    }
}