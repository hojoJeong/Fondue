package com.ssafy.fundyou.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ActivityMainBinding
import com.ssafy.fundyou.ui.home.MainFragmentDirections
import com.ssafy.fundyou.ui.splash.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        initNavigation()
        initFragmentByDeepLink()
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
            }
        }
        binding.bnvMain.setupWithNavController(navController)
    }

    private fun setBottomNavigationVisibility(visibility: Int) {
        binding.bnvMain.visibility = visibility
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
        val fundingId = intent.getStringExtra("funding_id")

        if (itemId != null) {
            navController.navigate(
                MainFragmentDirections.actionMainFragmentToItemDetailFragment(
                    itemId.toLong()
                )
            )
        } else if (fundingId != null) {
            navController.navigate(
                MainFragmentDirections.actionMainFragmentToInvitedFondueFragment(
                    fundingId.toLong()
                )
            )
        }
    }

    companion object {
        private const val TAG = "MainActivity..."
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