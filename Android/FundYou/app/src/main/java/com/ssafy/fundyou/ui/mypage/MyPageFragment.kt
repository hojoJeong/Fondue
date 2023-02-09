package com.ssafy.fundyou.ui.mypage

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentMyPageBinding
import com.ssafy.fundyou.ui.base.BaseFragment

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private val userInfoViewModel by activityViewModels<UserInfoViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModels()
    }

    override fun initView() {
        userInfoViewModel.getUserInfo()
        initClickListener()
    }

    override fun initViewModels() {
        initUserInfoObserve()
    }

    private fun initUserInfoObserve(){
        userInfoViewModel.userInfo.observe(viewLifecycleOwner){ response ->
            when(response){
                is ViewState.Loading ->{
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
        logoutCickListener()
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
            navigate(MyPageFragmentDirections.actionMyPageFragmentToMyFundingFragment())
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
            //TODO("로그인된 사용자 계정 정보 arg로 넘겨주기")
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

    private fun logoutCickListener() {
        binding.tvMypageLogout.setOnClickListener {
        }
    }
}