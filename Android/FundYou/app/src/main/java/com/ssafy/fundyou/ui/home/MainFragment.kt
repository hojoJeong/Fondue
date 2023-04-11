package com.ssafy.fundyou.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ScrollView
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.chip.Chip
import com.google.android.material.slider.RangeSlider
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentMainBinding
import com.ssafy.fundyou.domain.model.item.ProductItemModel
import com.ssafy.fundyou.ui.adapter.PopularSearchKeywordAdapter
import com.ssafy.fundyou.ui.adapter.MainRandomItemAdapter
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.ui.home.adapter.MainBannerAdapter
import com.ssafy.fundyou.ui.home.adapter.MainCategoryAdapter
import com.ssafy.fundyou.ui.adapter.ProductItemAdapter
import com.ssafy.fundyou.ui.home.model.MainCategoryModel
import com.ssafy.fundyou.ui.search.SearchViewModel
import com.ssafy.fundyou.util.view.RecyclerViewItemDecorator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import javax.inject.Inject


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {
    private val bannerImageList = mutableListOf<Int>()

    @Inject
    lateinit var mainBannerAdapter: MainBannerAdapter
    private val categoryList = mutableListOf<MainCategoryModel>()
    private val rankingProductList = mutableListOf<ProductItemModel>()
    private val popularSearchList = mutableListOf<String>()
    private var currentBannerPosition = 0
    private lateinit var job: Job

    private val searchViewModel by viewModels<SearchViewModel>()
    private val popularSearchAdapter = PopularSearchKeywordAdapter().apply {
        addItemClickEvent { MainFragmentDirections.actionMainFragmentToItemDetailFragment(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(bannerImageList) {
            add(R.drawable.bg_banner_motiondesk)
            add(R.drawable.bg_banner_samsung_bespoke)
            add(R.drawable.bg_banner_ssafylogo2)
            add(R.drawable.bg_banner_ssafylogo3)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        initBanner()
        initCategory()
        initRankCategory()
        initTitlePriceRange()
        initRankingItem()
        initShowMoreBtnListener()
        initFloatingBtn()
        initRandomItemList()
        initPopularSearch()
        initSearchClickEvent()
    }

    override fun initViewModels() {
        initSearchViewModel()
    }

    private fun initBanner() {
        val bannerSize = bannerImageList.size
        currentBannerPosition = Int.MAX_VALUE / 2 * bannerSize

        mainBannerAdapter.addAllItems(bannerImageList)

        binding.tvMainBannerIndicator.text = getString(
            R.string.content_banner_indicator, binding.vpMainBanner.currentItem + 1, bannerSize
        )

        with(binding.vpMainBanner) {
            adapter = mainBannerAdapter
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

    private fun initSearchClickEvent() {
        binding.editMainSearch.setOnClickListener {
            navigate(MainFragmentDirections.actionMainFragmentToSearchFragment())
        }
    }

    private fun initCategory() {
        //임시 데이터 추가
        categoryList.clear()
        categoryList.add(
            MainCategoryModel(
                ContextCompat.getDrawable(
                    requireContext(), R.drawable.bg_category_living
                )!!, "인테리어"
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

        val categoryAdapter = MainCategoryAdapter { categoryType ->
            val actionCategory =
                MainFragmentDirections.actionMainFragmentToItemListFragment(categoryType)
            navigate(actionCategory)
        }

        categoryAdapter.initCategoryItem(categoryList)

        binding.rvMainCategory.adapter = categoryAdapter
    }

    private fun setJobForBanner() {
        job = lifecycleScope.launchWhenResumed {
            delay(3000)
            binding.vpMainBanner.setCurrentItem(++currentBannerPosition, true)
        }
    }

    private fun initRankCategory() {
        binding.chipgMainRankCategory.setOnCheckedStateChangeListener { group, checkedId ->

            val checkedChip = group.getChildAt(0) as Chip
            val checkedChip2 = group.findViewById<Chip>(group.checkedChipId) as Chip
            Log.d("TAG", "initRankCategory: ${checkedChip2.text}")
            //TODO(칩 선택 변경 시 서버통신 추가)
        }
    }

    private fun initTitlePriceRange() {
        with(binding.sldMainRank) {
            addOnChangeListener { slider, _, _ ->
                val sliderValueList = slider.values
                val minPrice = sliderValueList[0].toInt()
                val maxPrice = sliderValueList[sliderValueList.size - 1].toInt()
                binding.tvMainRankPriceRange.text =
                    getString(R.string.title_rank_price_range, minPrice, maxPrice)
            }
            addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: RangeSlider) {

                }

                override fun onStopTrackingTouch(slider: RangeSlider) {

                    //가격 범위 변경 시 서버통신 추가
                }
            })
        }
    }

    private fun initRankingItem() {
        //임시 데이터 추가
        rankingProductList.clear()
        rankingProductList.add(
            ProductItemModel(
                0,
                100000,
                "",
                "BESPOKE 냉장고",
                false,
                "삼성",
                true
            )
        )
        rankingProductList.add(
            ProductItemModel(
                1,
                100000,
                "",
                "BESPOKE 냉장고",
                true,
                "삼성",
                false
            )
        )
        rankingProductList.add(
            ProductItemModel(
                2,
                100000,
                "",
                "BESPOKE 냉장고",
                false,
                "삼성",
                false
            )
        )
        rankingProductList.add(
            ProductItemModel(
                3,
                100000,
                "",
                "BESPOKE 냉장고",
                true,
                "삼성",
                true
            )
        )
        rankingProductList.add(
            ProductItemModel(
                4,
                100000,
                "",
                "BESPOKE 냉장고",
                false,
                "삼성",
                true
            )
        )
        rankingProductList.add(
            ProductItemModel(
                5,
                100000,
                "",
                "BESPOKE 냉장고",
                true,
                "삼성",
                false
            )
        )


        val rankingItemAdapter = ProductItemAdapter()
        rankingItemAdapter.checkNeedRanking(true)
        rankingItemAdapter.submitList(rankingProductList)
        with(binding.rvMainRank) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = rankingItemAdapter
        }

    }

    private fun initRandomItemList() {
        val randomAdapter = MainRandomItemAdapter()
        val spanCount = 3
        randomAdapter.submitList(rankingProductList)

        with(binding.rvMainRandom) {
            layoutManager =
                GridLayoutManager(requireContext(), spanCount, GridLayoutManager.VERTICAL, false)
            adapter = randomAdapter
            addItemDecoration(RecyclerViewItemDecorator(0, 0, 30, 0, spanCount))
        }
    }

    private fun initShowMoreBtnListener() {
        binding.btnMainRandomShowMore.setOnClickListener {

            val actionShowMoreBtn =
                MainFragmentDirections.actionMainFragmentToItemListFragment(getString(R.string.title_category_all))
            navigate(actionShowMoreBtn)
        }
    }

    private fun initPopularSearch() {
        val spanCount = 2
        searchViewModel.getPopularKeywordList()
        with(binding.rvMainPopularSearch) {
            layoutManager =
                GridLayoutManager(requireContext(), spanCount, GridLayoutManager.VERTICAL, false)
            adapter = popularSearchAdapter
            addItemDecoration(RecyclerViewItemDecorator(0, 0, 30, 0, spanCount))
        }
    }

    private fun initFloatingBtn() {
        val scrollView = binding.svMain
        val scrollUpBtn = binding.btnMainScrollUp
        scrollUpBtn.visibility = View.GONE

        scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, _, _, _ ->
            if (v.scrollY > -1) {
                scrollUpBtn.visibility = View.VISIBLE
            }
            if (!v.canScrollVertically(-1)) {
                scrollUpBtn.visibility = View.GONE
            }
        })

        scrollUpBtn.setOnClickListener {
            scrollView.post {
                scrollView.fullScroll(ScrollView.FOCUS_UP)
            }
        }
    }

    private fun initSearchViewModel() {
        searchViewModel.popularKeywordList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initGetPopularKeywordListObserve: Loading...")
                }
                is ViewState.Success -> {
                    popularSearchAdapter.submitList(response.value)
                }
                is ViewState.Error -> {
                    Log.d(
                        TAG, "initGetPopularKeywordListObserve: Error... ${response.message}"
                    )
                }
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

    companion object {
        private const val TAG = "MainFragment..."
    }
}