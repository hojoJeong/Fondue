package com.ssafy.fundyou.ui.mypage

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentMyPageBinding
import com.ssafy.fundyou.ui.common.BaseFragment
import com.ssafy.fundyou.ui.login.LoginActivity
import com.ssafy.fundyou.util.showSnackBar

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private val mypageViewModel by activityViewModels<MyPageViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModels()
    }

    override fun initView() {
        mypageViewModel.getUserInfo()
        initClickListener()
        initLoadPointBtnListener()
    }

    override fun initViewModels() {
        initUserInfoObserve()
        initResultLogoutObserve()
    }

    private fun initLoadPointBtnListener() {
        binding.btnMypagePoint.setOnClickListener {
            navigate(
                MyPageFragmentDirections.actionMyPageFragmentToPointLoadFragment(
                    mypageViewModel.userInfo.value?.value?.point ?: 0
                )
            )
        }
    }

    private fun initUserInfoObserve() {
        mypageViewModel.userInfo.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initUserInfoObserve: UserInfo Loading...")
                }
                is ViewState.Success -> {
                    val userInfo = response.value
                    binding.user = userInfo
                    Log.d(TAG, "initUserInfoObserve: $userInfo")
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initUserInfoObserve: UserInfo Loading Error...${response.message}")
                }
            }
        }
    }

    private fun initClickListener() {
        userInfoClickListener()
        notiSettingClickListener()
        privacyCickListener()
        termsOfServiceClickListener()
        logoutClickListener()
        invitedFondueClickListener()
        myFondueClickListener()
        favoriteClickListener()
    }

    private fun invitedFondueClickListener() {
        binding.ivMypageInvitedFondue.setOnClickListener {
            navigate(MyPageFragmentDirections.actionMyPageFragmentToInvitedFondueFragment())
        }
    }

    private fun myFondueClickListener() {
        binding.ivMypageMyfondue.setOnClickListener {

        }
    }

    private fun favoriteClickListener() {
        binding.ivMypageFavorite.setOnClickListener {
            navigate(MyPageFragmentDirections.actionMyPageFragmentToFavoriteFragment())
        }
    }

    private fun userInfoClickListener() {
        binding.tvMypageUserInformation.setOnClickListener {
            navigate(MyPageFragmentDirections.actionMyPageFragmentToUserInfoFragment())
        }
    }

    private fun notiSettingClickListener() {
        binding.tvMypageNotificationSetting.setOnClickListener {
            navigate(MyPageFragmentDirections.actionMyPageFragmentToNotiSettingFragment())
        }
    }

    private fun privacyCickListener() {
        binding.tvMypagePrivacy.setOnClickListener {
            navigate(MyPageFragmentDirections.actionMyPageFragmentToPrivacyFragment("privacy"))
        }
    }

    private fun termsOfServiceClickListener() {
        binding.tvMypageTermsOfService.setOnClickListener {
            navigate(MyPageFragmentDirections.actionMyPageFragmentToPrivacyFragment("service"))
        }
    }

    private fun logoutClickListener() {
        binding.tvMypageLogout.setOnClickListener {
            mypageViewModel.clearAuthPreference()
        }
    }

    private fun initResultLogoutObserve(){
        mypageViewModel.resultLogout.observe(viewLifecycleOwner){ response ->
            when(response){
                is ViewState.Loading -> {
                    Log.d(TAG, "initResultLogoutObserve: Logout Loading...")
                }
                is ViewState.Success -> {
                    Log.d(TAG, "initResultLogoutObserve: Logout Success...")
                    val intent = Intent(requireActivity(), LoginActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    startActivity(intent)
                }
                is ViewState.Error -> {
                    requireView().showSnackBar("로그아웃에 실패했습니다. 잠시 후 다시 시도해주세요.")
                }
            }
        }
    }
}