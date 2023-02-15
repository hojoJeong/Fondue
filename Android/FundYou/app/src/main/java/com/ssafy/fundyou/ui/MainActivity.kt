package com.ssafy.fundyou.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.messaging.FirebaseMessaging
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.ActivityMainBinding
import com.ssafy.fundyou.domain.usecase.fcm.AddFcmTokenUseCase
import com.ssafy.fundyou.ui.home.MainFragmentDirections
import com.ssafy.fundyou.ui.home.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var addFcmTokenUseCase: AddFcmTokenUseCase

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private var itemFlag = false
    private var fundingId: String? = ""
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        initNavigation()
        initFragmentByDeepLink()
        initSaveFundingInfoObserver()
        initBottomNavigation()
        initGetFcmToken()
    }

    private fun initNavigation() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_main) as NavHostFragment
        navController = navHostFragment.findNavController()
        navController.addOnDestinationChangedListener { _, _, _ ->
            when (navController.currentDestination?.id) {
                R.id.mainFragment -> {
                    setToolbarType(ToolbarType.LOGO_WISH)
                    setBottomNavigationVisibility(View.VISIBLE)
                }
                R.id.searchFragment -> {
                    setToolbarType(ToolbarType.TEXT, "검색")
                    setBottomNavigationVisibility(View.VISIBLE)
                }
                R.id.itemListFragment -> {
                    setToolbarType(ToolbarType.BACK_TITLE_WISH, "상품 목록")
                    setBottomNavigationVisibility(View.GONE)
                }
                R.id.itemDetailFragment -> {
                    setToolbarType(ToolbarType.BACK_TITLE_WISH, "상품 상세")
                    setBottomNavigationVisibility(View.GONE)
                }
                R.id.myFundingFragment -> {
                    setToolbarType(ToolbarType.TEXT, "나의 펀딩")
                    setBottomNavigationVisibility(View.GONE)
                }
                R.id.myFundingDetailFragment -> {
                    setToolbarType(ToolbarType.TEXT, "펀딩 통계")
                    setBottomNavigationVisibility(View.GONE)
                }
                R.id.myFundingItemDetailFragment -> {
                    setToolbarType(ToolbarType.TEXT_CANCEL, "펀딩 상세")
                    setBottomNavigationVisibility(View.GONE)
                }
                R.id.searchResultFragment -> {
                    setToolbarType(ToolbarType.NO)
                    setBottomNavigationVisibility(View.GONE)
                }
                R.id.wishListFragment -> {
                    setToolbarType(ToolbarType.TEXT_CANCEL, "위시리스트")
                    setBottomNavigationVisibility(View.GONE)
                }
                R.id.myFundingListFragment -> {
                    setToolbarType(ToolbarType.TEXT, "내 퐁듀")
                }
                R.id.fundingParticipateFragment -> {
                    binding.lyToolbar.root.visibility = View.GONE
                    setBottomNavigationVisibility(View.GONE)
                }
                R.id.favoriteFragment -> {
                    setToolbarType(ToolbarType.TEXT, "찜 목록")
                    setBottomNavigationVisibility(View.VISIBLE)
                }
                R.id.myPageFragment -> {
                    setToolbarType(ToolbarType.TEXT, "마이페이지")
                    setBottomNavigationVisibility(View.VISIBLE)
                }
                R.id.FundingParticipateListFragment -> {
                    setToolbarType(ToolbarType.TEXT, "초대 받은 퐁듀")
                    setBottomNavigationVisibility(View.GONE)
                }
                R.id.userInfoFragment -> {
                    setToolbarType(ToolbarType.BACK_TEXT, "계정 기본 정보")
                    setBottomNavigationVisibility(View.GONE)
                }
                R.id.notiSettingFragment -> {
                    setToolbarType(ToolbarType.BACK_TEXT, "알림 설정")
                    setBottomNavigationVisibility(View.GONE)
                }
                R.id.privacyFragment -> {
                    setToolbarType(ToolbarType.BACK_TEXT, "개인정보 수집 방침")
                    setBottomNavigationVisibility(View.GONE)
                }
                R.id.cancelMenbershipFragment -> {
                    setToolbarType(ToolbarType.BACK_TEXT, "회원탈퇴")
                    setBottomNavigationVisibility(View.GONE)
                }
                R.id.arGalleryFragment -> {
                    setToolbarType(ToolbarType.BACK_TEXT, "AR 등록")
                    setBottomNavigationVisibility(View.GONE)
                }
                R.id.galleryDetailFragment -> {
                    setToolbarType(ToolbarType.NO)
                    setBottomNavigationVisibility(View.GONE)
                }
                R.id.arCaptureFragment -> {
                    setToolbarType(ToolbarType.BACK_TEXT, "캡처 결과")
                    setBottomNavigationVisibility(View.GONE)
                }
                R.id.arFragment -> {
                    setToolbarType(ToolbarType.NO)
                    setBottomNavigationVisibility(View.GONE)
                }
                R.id.fundingParticipateItemFragment -> {
                    setToolbarType(ToolbarType.BACK_TEXT, "상품 상세")
                    setBottomNavigationVisibility(View.GONE)
                }
                R.id.payFragment -> {
                    setToolbarType(ToolbarType.TEXT_CANCEL, "펀딩하기")
                    setBottomNavigationVisibility(View.GONE)
                }
                R.id.payResultFragment -> {
                    setToolbarType(ToolbarType.TEXT_CANCEL, "펀딩 결과")
                    setBottomNavigationVisibility(View.GONE)
                }
            }
        }
    }

    private fun setBottomNavigationVisibility(visibility: Int) {
        binding.bnvMain.visibility = visibility
    }

    private fun initBottomNavigation() {
        binding.bnvMain.setupWithNavController(navController)

        binding.bnvMain.apply {
            navController.let { navController ->
                NavigationUI.setupWithNavController(
                    this,
                    navController
                )
                setOnItemSelectedListener { item ->
                    NavigationUI.onNavDestinationSelected(item, navController)
                    true
                }
                setOnItemReselectedListener {
                    navController.popBackStack(destinationId = it.itemId, inclusive = false)
                }
            }
        }
    }

    private fun setToolbarType(type: ToolbarType, title: String = "") {
        toolbarClear()
        setToolbarTitle(title)
        binding.lyToolbar.root.visibility = View.VISIBLE
        when (type) {
            ToolbarType.LOGO_WISH -> {
                setToolbarLeft(ToolbarLeftImg.LOGO)
                setToolbarRight(ToolbarRightImg.WISH)
            }
            ToolbarType.BACK_TITLE_WISH -> {
                setToolbarLeft(ToolbarLeftImg.BACK)
                setToolbarRight(ToolbarRightImg.WISH)
            }
            ToolbarType.TEXT_CANCEL -> {
                setToolbarRight(ToolbarRightImg.CANCEL)
            }
            ToolbarType.BACK_TEXT -> {
                setToolbarLeft(ToolbarLeftImg.BACK)
            }
            ToolbarType.NO -> {
                binding.lyToolbar.root.visibility = View.GONE
            }
            // 실행 내용 없음
            ToolbarType.TEXT -> {}
        }
    }

    private fun setToolbarLeft(type: ToolbarLeftImg) {
        when (type) {
            ToolbarLeftImg.BACK -> {
                with(binding.lyToolbar.ivLeftImg) {
                    setOnClickListener {
                        navController.popBackStack()
                    }
                    setImageResource(R.drawable.ic_back_arrow_no_line)
                }
            }
            ToolbarLeftImg.LOGO -> {
                binding.lyToolbar.ivLeftImg.setImageResource(R.drawable.ic_app_logo)
            }
        }
    }

    private fun setToolbarRight(type: ToolbarRightImg) {
        when (type) {
            ToolbarRightImg.WISH -> {
                with(binding.lyToolbar.ivRightImg) {
                    setImageResource(R.drawable.ic_wish_list)
                    setOnClickListener {
                        navController.navigate(R.id.wishListFragment)
                    }
                }
            }
            ToolbarRightImg.CANCEL -> {
                with(binding.lyToolbar.ivRightImg) {
                    setImageResource(R.drawable.ic_cancel)
                    setOnClickListener {
                        navController.popBackStack()
                    }
                }
            }
        }
    }

    private fun toolbarClear() {
        with(binding.lyToolbar) {
            ivLeftImg.setImageResource(0)
            tvTitle.text = ""
            ivRightImg.setImageResource(0)
        }
    }

    private fun setToolbarTitle(title: String) {
        binding.lyToolbar.tvTitle.text = title
    }

    private fun initFragmentByDeepLink() {
        val itemId = intent.getStringExtra("item_id")
        val getFundingId = intent.getStringExtra("funding_id")
        fundingId = getFundingId
        if (!itemFlag) {
            if (itemId != null) {
                navController.navigate(
                    MainFragmentDirections.actionMainFragmentToItemDetailFragment(
                        itemId.toLong()
                    )
                )
            } else if (getFundingId != null) {
                mainViewModel.saveFundingInfo(getFundingId.toLong())
            }
        }
        itemFlag = true
    }

    private fun initSaveFundingInfoObserver() {
        mainViewModel.savedFundingId.observe(this) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    navController.navigate(
                        MainFragmentDirections.actionMainFragmentToFundingParticipateFragment(
                            fundingId?.toLong()!!
                        )
                    )
                }
                is ViewState.Error -> {

                }
            }
        }
    }

    private fun initGetFcmToken() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Main) {
                val token = FirebaseMessaging.getInstance().token.toString()
                Log.d(TAG, "initFcm: FCM Token : $token")
                addFcmTokenUseCase(token)
            }
            Log.d(TAG, "onNewToken: addToken success")
        }
    }
}

enum class ToolbarType {
    LOGO_WISH, BACK_TITLE_WISH, TEXT_CANCEL, BACK_TEXT, NO, TEXT
}

enum class ToolbarLeftImg {
    LOGO, BACK
}

enum class ToolbarRightImg {
    WISH, CANCEL
}