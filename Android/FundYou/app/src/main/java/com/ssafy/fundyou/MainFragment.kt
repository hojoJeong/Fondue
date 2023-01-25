package com.ssafy.fundyou

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.fundyou.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val sliderImageHandler: Handler by lazy { Handler(Looper.getMainLooper()) }
    private val sliderImageRunnable = Runnable{binding.vpMainBanner.currentItem = binding.vpMainBanner.currentItem + 1}
    private val imageList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imageList.apply {
            add(R.drawable.bg_banner_ssafylogo2)
            add(R.drawable.bg_banner_ssafylogo2)
            add(R.drawable.bg_banner_ssafylogo3)
            add(R.drawable.bg_banner_ssafylogo3)
            add(R.drawable.bg_banner_ssafylogo3)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bannerSize = imageList.size
        binding.tvMainBannerIndicator.text = "1 / $bannerSize"

        binding.vpMainBanner.apply {
            adapter = BannerAdapter(imageList, binding.vpMainBanner)
            offscreenPageLimit = 1
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.tvMainBannerIndicator.text = "${position%bannerSize+1} / $bannerSize"
                    sliderImageHandler.removeCallbacks(sliderImageRunnable)
                    sliderImageHandler.postDelayed(sliderImageRunnable, 3000)
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        sliderImageHandler.postDelayed(sliderImageRunnable, 3000)
    }

    override fun onPause() {
        super.onPause()
        sliderImageHandler.removeCallbacks(sliderImageRunnable)
    }
}