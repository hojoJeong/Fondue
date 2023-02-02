package com.ssafy.fundyou.ui.mypage

import android.os.Bundle
import android.view.View
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentMyPageBinding
import com.ssafy.fundyou.ui.base.BaseFragment

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    override fun initView() {
        initClickListener()
    }

    override fun initViewModels() {
    }

    private fun initClickListener(){
        userInfoClickListener()
        notiSettingClickListener()
        privacyCickListener()
        termsOfServiceClickListener()
        logoutCickListener()
        invitedFondueClickListener()
        favoriteClickListener()
    }

    private fun invitedFondueClickListener(){
        binding.ivMypageInvitedFondue.setOnClickListener {
            navigate(MyPageFragmentDirections.actionMyPageFragmentToInvitedFondueFragment())
        }
    }

    private fun favoriteClickListener(){
        binding.ivMypageFavorite.setOnClickListener {
            navigate(MyPageFragmentDirections.actionMyPageFragmentToFavoriteFragment())
        }
    }

    private fun userInfoClickListener(){
        binding.tvMypageUserInformation.setOnClickListener {
            navigate(MyPageFragmentDirections.actionMyPageFragmentToUserInfoFragment())
        }
    }

    private fun notiSettingClickListener(){
        binding.tvMypageNotificationSetting.setOnClickListener {
            //TODO("로그인된 사용자 계정 정보 arg로 넘겨주기")
            navigate(MyPageFragmentDirections.actionMyPageFragmentToNotiSettingFragment())
        }
    }

    private fun privacyCickListener(){
        binding.tvMypagePrivacy.setOnClickListener {
            navigate(MyPageFragmentDirections.actionMyPageFragmentToPrivacyFragment("privacy"))
        }
    }

    private fun termsOfServiceClickListener(){
        binding.tvMypageTermsOfService.setOnClickListener {
            navigate(MyPageFragmentDirections.actionMyPageFragmentToPrivacyFragment("service"))
        }
    }

    private fun logoutCickListener(){
        binding.tvMypageLogout.setOnClickListener {

        }
    }

}