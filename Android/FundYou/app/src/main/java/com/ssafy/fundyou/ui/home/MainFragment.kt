package com.ssafy.fundyou.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ScrollView
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.activityViewModels
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
import com.ssafy.fundyou.ui.common.adapter.MainRandomItemAdapter
import com.ssafy.fundyou.ui.common.adapter.PopularSearchKeywordAdapter
import com.ssafy.fundyou.ui.common.BaseFragment
import com.ssafy.fundyou.ui.home.adapter.MainBannerAdapter
import com.ssafy.fundyou.ui.home.adapter.MainCategoryAdapter
import com.ssafy.fundyou.ui.home.adapter.MainRankingItemAdapter
import com.ssafy.fundyou.ui.home.model.MainCategoryModel
import com.ssafy.fundyou.ui.home.model.RandomItemModel
import com.ssafy.fundyou.ui.home.model.RankingItemModel
import com.ssafy.fundyou.ui.search.SearchViewModel
import com.ssafy.fundyou.util.view.RecyclerViewItemDecorator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {
    val CATEGORY_ALL = 0
    val MIN_PRICE = 0
    val MAX_PRICE = 1000000
    private val mainViewModel by activityViewModels<MainViewModel>()

    private val bannerImageList = mutableListOf<Int>()
    private var currentBannerPosition = 0
    private lateinit var job: Job

    private val searchViewModel by viewModels<SearchViewModel>()
    private val popularSearchAdapter = PopularSearchKeywordAdapter().apply {
        addItemClickEvent { MainFragmentDirections.actionMainFragmentToSearchResultFragment(it) }
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
        initRankingPriceRange()
        initShowMoreBtnListener()
        initFloatingBtn()
        initPopularSearch()
        initSearchClickEvent()
        mainViewModel.getRankingItemList(CATEGORY_ALL, MIN_PRICE, MAX_PRICE)
        mainViewModel.getRandomItemList()
    }

    override fun initViewModels() {
        initRankingItemObserve()
        initRandomItemObserve()
        initSearchViewModel()
    }

    private fun initBanner() {
        val bannerSize = bannerImageList.size
        currentBannerPosition = Int.MAX_VALUE / 2 * bannerSize

        val mainBannerAdapter = MainBannerAdapter()
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

    private fun setJobForBanner() {
        job = lifecycleScope.launchWhenResumed {
            delay(3000)
            binding.vpMainBanner.setCurrentItem(++currentBannerPosition, true)
        }
    }

    private fun initSearchClickEvent() {
        binding.editMainSearch.setOnClickListener {
            navigate(MainFragmentDirections.actionMainFragmentToSearchFragment())
        }
    }

    private fun initCategory() {
        //임시 데이터 추가
        val categoryList = mutableListOf<MainCategoryModel>()
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

    private fun initRankCategory() {
        binding.chipgMainRankCategory.setOnCheckedStateChangeListener { group, checkedId ->
            val checkedCategory = group.findViewById(group.checkedChipId) as Chip
            Log.d("TAG", "initRankCategory: ${checkedCategory.text}")

            val categoryId = resources.getStringArray(R.array.category_num)
                .indexOf(checkedCategory.text.toString())

            with(mainViewModel) {
                setCategory(categoryId)
                getRankingItemList(
                    rankingCategoryId.value ?: CATEGORY_ALL,
                    rankingMinPrice.value ?: MIN_PRICE,
                    rankingMaxPrice.value ?: MAX_PRICE
                )
            }

        }
    }

    private fun initRankingPriceRange() {
        var minPrice = MIN_PRICE
        var maxPrice = MAX_PRICE
        with(binding.sldMainRank) {
            addOnChangeListener { slider, _, _ ->
                val sliderValueList = slider.values
                minPrice = (sliderValueList[0].toInt())
                maxPrice = (sliderValueList[sliderValueList.size - 1].toInt())
                binding.tvMainRankPriceRange.text =
                    getString(R.string.title_rank_price_range, minPrice, maxPrice)
            }
            addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: RangeSlider) {}

                override fun onStopTrackingTouch(slider: RangeSlider) {
                    with(mainViewModel) {
                        setPrice(minPrice * 10000, maxPrice * 10000)
                        getRankingItemList(
                            rankingCategoryId.value ?: CATEGORY_ALL,
                            rankingMinPrice.value ?: MIN_PRICE,
                            rankingMaxPrice.value ?: MAX_PRICE
                        )
                        Log.d(
                            TAG,
                            "onStopTrackingTouch 가격 설정 : ${mainViewModel.rankingMaxPrice.value}, ${mainViewModel.rankingMinPrice.value}"
                        )
                    }
                }
            })
        }
    }

    private fun initRankingItemObserve() {
        mainViewModel.rankingItemList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initRankingItemObserve: Ranking Item Loading...")
                }
                is ViewState.Success -> {
                    initRankingItemAdapter(response.value ?: emptyList())
                }
                is ViewState.Error -> {
                    Log.d(
                        TAG,
                        "initRankingItemObserve: Ranking Item Loading Error...${response.message}"
                    )
                }
            }
        }
    }

    private fun initRandomItemObserve() {
        mainViewModel.randomItemList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initRandomItemObserve: Random Item Loading...")
                }
                is ViewState.Success -> {
                    initRandomItemAdapter(response.value ?: emptyList())
                }
                is ViewState.Error -> {
                    Log.d(
                        TAG,
                        "initRandomItemObserve: Random Item Loading Error...${response.message}"
                    )
                }
            }
        }
    }

    private fun initRankingItemAdapter(rankingItemList: List<RankingItemModel>) {
        val rankingItemAdapter = MainRankingItemAdapter()
        rankingItemAdapter.submitList(rankingItemList)
        rankingItemAdapter.addItemClickListener { id ->
            navigate(MainFragmentDirections.actionMainFragmentToItemDetailFragment(id))
        }
        with(binding.rvMainRank) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = rankingItemAdapter
        }
    }

    private fun initRandomItemAdapter(randomItemList: List<RandomItemModel>) {
        val randomAdapter = MainRandomItemAdapter()
        val spanCount = 3
        randomAdapter.submitList(randomItemList)

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