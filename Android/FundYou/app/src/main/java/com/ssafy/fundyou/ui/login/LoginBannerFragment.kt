package com.ssafy.fundyou.ui.login

import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentLoginBannerBinding
import com.ssafy.fundyou.ui.login.adapter.LoginBannerAdapter
import com.ssafy.fundyou.ui.login.model.LoginBannerModel

class LoginBannerFragment : Fragment() {

    private lateinit var binding : FragmentLoginBannerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_banner, container, false, )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = if(VERSION.SDK_INT >= 33) {
            arguments?.getParcelable("LOGIN_BANNER", LoginBannerModel::class.java)
        } else{
            arguments?.getParcelable("LOGIN_BANNER")
        }

        binding.bannerModel = data
    }
}