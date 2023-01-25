package com.ssafy.fundyou

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.fundyou.databinding.ContainerMainBannerBinding

class BannerAdapter(
    private val bannerItems: MutableList<Int>,
    private var banner: ViewPager2
) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    class BannerViewHolder(val binding: ContainerMainBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(image: Int) {
            binding.ivMainBanner.setImageResource(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val imageBinding =
            ContainerMainBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(imageBinding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.onBind(bannerItems[position])
        if (position == bannerItems.size - 1) {
            banner.post(runnable)
        }
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    private val runnable = Runnable { bannerItems.addAll(bannerItems) }
}