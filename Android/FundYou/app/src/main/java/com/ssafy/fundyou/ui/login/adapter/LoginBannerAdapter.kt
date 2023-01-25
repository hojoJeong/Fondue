package com.ssafy.fundyou.ui.login.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ssafy.fundyou.ui.login.LoginBannerFragment
import com.ssafy.fundyou.ui.login.model.LoginBannerModel

class LoginBannerAdapter(fm : FragmentActivity) : FragmentStateAdapter(fm) {

    private val bannerList = mutableListOf<LoginBannerModel>()

    override fun getItemCount() = bannerList.size

    override fun createFragment(position: Int): Fragment {
        return LoginBannerFragment().apply {
            arguments = Bundle().apply {
                putParcelable("LOGIN_BANNER", bannerList[position])
            }
        }
    }

    fun addAllBannerList(list : List<LoginBannerModel>){
        bannerList.addAll(list)
    }
}