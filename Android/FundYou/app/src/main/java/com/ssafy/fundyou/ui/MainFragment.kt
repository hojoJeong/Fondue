package com.ssafy.fundyou.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.slider.RangeSlider
import com.ssafy.fundyou.*
import com.ssafy.fundyou.databinding.FragmentMainBinding
import com.ssafy.fundyou.domain.model.ProductItemlModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val bannerImageList = mutableListOf<Int>()
    private val categoryList = mutableListOf<MainCategoryModel>()
    private val rankingProductList = mutableListOf<ProductItemlModel>()
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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init(){
        initBanner()
        initCategory()
        initRankCategory()
        initTitlePriceRange()
        initRankingItem()
        initFloatingBtn()
        initRandomToday()
    }

    private fun initBanner() {
        val bannerSize = bannerImageList.size
        currentBannerPosition = Int.MAX_VALUE / 2 * bannerSize

        val bannerAdapter = BannerAdapter()
        bannerAdapter.addAllItems(bannerImageList)

        binding.tvMainBannerIndicator.text = getString(
            R.string.content_banner_indicator, binding.vpMainBanner.currentItem + 1, bannerSize
        )

        with(binding.vpMainBanner) {
            adapter = bannerAdapter
            setCurrentItem(currentBannerPosition, false)

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    currentBannerPosition = position
                    binding.tvMainBannerIndicator.text = getString(
                        R.string.content_banner_indicator,
                        (currentBannerPosition % bannerSize) + 1,
                        bannerSize
                    )
                }

                override fun onPageScrollStateChanged(state: Int) {
                    when (state) {
                        ViewPager2.SCROLL_STATE_IDLE -> {
                            if (!job.isActive) {
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

    private fun initCategory() {
        //임시 데이터 추가
        categoryList.add(
            MainCategoryModel(
                ContextCompat.getDrawable(
                    requireContext(), R.drawable.bg_category_living
                )!!, "리빙/인테리어"
            )
        )
        categoryList.add(
            MainCategoryModel(
                ContextCompat.getDrawable(
                    requireContext(), R.drawable.bg_category_digital
                )!!, "디지털/가전"
            )
        )
        categoryList.add(
            MainCategoryModel(
                ContextCompat.getDrawable(
                    requireContext(), R.drawable.bg_category_kitchen
                )!!, "주방용품"
            )
        )
        categoryList.add(
            MainCategoryModel(
                ContextCompat.getDrawable(
                    requireContext(), R.drawable.bg_category_etc
                )!!, "기타"
            )
        )

        val categoryAdapter = MainCategoryAdapter()
        categoryAdapter.initCategoryItem(categoryList, this)

        binding.rvMainCategory.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)
            adapter = categoryAdapter
        }
    }

    private fun setJobForBanner() {
        job = lifecycleScope.launchWhenResumed {
            delay(3000)
            binding.vpMainBanner.setCurrentItem(++currentBannerPosition, true)
        }
    }

    private fun initRankCategory() {
        binding.chipgMainRankCategory.setOnCheckedStateChangeListener { group, checkedId ->


            //칩 선택 변경 시 서버통신 추가
        }
    }

    private fun initTitlePriceRange(){
        binding.sldMainRank.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener{
            override fun onStartTrackingTouch(slider: RangeSlider){}

            override fun onStopTrackingTouch(slider: RangeSlider) {
                val sliderValueList = binding.sldMainRank.values
                val minPrice = sliderValueList[0].toInt()
                val maxPrice = sliderValueList[sliderValueList.size-1].toInt()
                binding.tvMainRankPriceRange.text = getString(R.string.title_rank_price_range, minPrice, maxPrice)

                //가격 범위 변경 시 서버통신 추가
            }
        })
    }

    private fun initRankingItem(){
        //임시 데이터 추가
        rankingProductList.add(ProductItemlModel(0, "100,000 원", "", "BESPOKE 냉장고", false, "삼성", true))
        rankingProductList.add(ProductItemlModel(1, "100,000 원", "", "BESPOKE 냉장고", true, "삼성", false))
        rankingProductList.add(ProductItemlModel(2, "100,000 원", "", "BESPOKE 냉장고", false, "삼성", false))
        rankingProductList.add(ProductItemlModel(3, "100,000 원", "", "BESPOKE 냉장고", true, "삼성", true))
        rankingProductList.add(ProductItemlModel(4, "100,000 원", "", "BESPOKE 냉장고", false, "삼성", true))
        rankingProductList.add(ProductItemlModel(5, "100,000 원", "", "BESPOKE 냉장고", true, "삼성", false))


        val rankingItemAdapter = ProductItemAdapter()
        rankingItemAdapter.submitList(rankingProductList)

        with(binding.rvMainRank){
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = rankingItemAdapter
        }

    }

    private fun initRandomToday(){
        val randomAdapter = MainRandomItemAdapter()
        randomAdapter.submitList(rankingProductList)

        with(binding.rvMainRandom){
            layoutManager = GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)
            adapter = randomAdapter
        }
    }

    private fun initFloatingBtn(){
        val scrollView = binding.svMain
        val scrollUpBtn = binding.btnMainScrollUp
        scrollUpBtn.visibility = View.GONE

        scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, _, _, _ ->
            if(v.scrollY > -1){
                scrollUpBtn.visibility = View.VISIBLE
            }
            if(!v.canScrollVertically(-1)){
                scrollUpBtn.visibility = View.GONE
            }
        })

        scrollUpBtn.setOnClickListener {
            scrollView.post {
                scrollView.fullScroll(ScrollView.FOCUS_UP)
            }
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

@BindingAdapter("bindImage")
fun bindImageFromRes(view: ImageView, drawable: Drawable) {
    view.setImageDrawable(drawable)
}