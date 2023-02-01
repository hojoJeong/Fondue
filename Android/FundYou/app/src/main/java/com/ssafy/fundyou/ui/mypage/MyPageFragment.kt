package com.ssafy.fundyou.ui.mypage

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentMyPageBinding
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.ui.home.MainFragmentDirections

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
        initFavoriteClickListener()
    }

    private fun initFavoriteClickListener(){
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
            navigate(MyPageFragmentDirections.actionMyPageFragmentToPrivacyFragment())
        }
    }

    private fun termsOfServiceClickListener(){
        binding.tvMypageTermsOfService.setOnClickListener {

        }
    }

    private fun logoutCickListener(){
        binding.tvMypageLogout.setOnClickListener {

        }
    }

}