package com.ssafy.fundyou

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.fundyou.databinding.FragmentMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val bannerImageList = mutableListOf<Int>()
    private var currentBannerPosition = 0
    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bannerImageList.apply {
            add(R.drawable.bg_banner_motiondesk)
            add(R.drawable.bg_banner_samsung_bespoke)
            add(R.drawable.bg_banner_ssafylogo2)
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

        bannerInit()

    }


    private fun bannerInit() {
        val bannerSize = bannerImageList.size
//        currentBannerPosition = Int.MAX_VALUE / 2 - ceil(bannerImageList.size.toDouble() / 2).toInt()

        val bannerAdapter = BannerAdapter()
        bannerAdapter.addAllItems(bannerImageList)

        binding.tvMainBannerIndicator.text = getString(R.string.content_banner_indicator, binding.vpMainBanner.currentItem+1, bannerSize)

        with(binding.vpMainBanner) {
            adapter = bannerAdapter
            setCurrentItem(currentBannerPosition, false)

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    currentBannerPosition = position
                    binding.tvMainBannerIndicator.text = getString(R.string.content_banner_indicator, (currentBannerPosition % bannerSize)+1, bannerSize)
                }

                override fun onPageScrollStateChanged(state: Int) {
                    when(state){
                        ViewPager2.SCROLL_STATE_IDLE ->{
                            if(!job.isActive){
                                setJobForBanner()
                            }
                        }

                        ViewPager2.SCROLL_STATE_DRAGGING -> {
                            job.cancel()
                        }

                        ViewPager2.SCROLL_STATE_SETTLING -> {}
                    }
                }
            })
        }
    }

    private fun setJobForBanner(){
        job = lifecycleScope.launchWhenResumed {
            delay(3000)
            binding.vpMainBanner.setCurrentItem(++currentBannerPosition, true)
        }
    }
    override fun onResume() {
        super.onResume()
        setJobForBanner()
    }

    override fun onPause() {
        super.onPause()
        job.cancel()
    }
}