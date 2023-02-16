package com.ssafy.fundyou.ui.mypage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentUserInfoBinding
import com.ssafy.fundyou.ui.common.BaseFragment
import com.ssafy.fundyou.ui.mypage.model.UserInfoModel

class UserInfoFragment : BaseFragment<FragmentUserInfoBinding>(R.layout.fragment_user_info) {
    private val userInfoViewModel by activityViewModels<MyPageViewModel>()
    private lateinit var userInfo: UserInfoModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {

        userInfo = userInfoViewModel.userInfo.value?.value!!
        binding.user = userInfo
        membershipWithdrawalClickListener()
    }

    override fun initViewModels() {
    }

    private fun membershipWithdrawalClickListener(){
        binding.tvUserinfoCancelMembershipTitle.setOnClickListener {
            navigate(UserInfoFragmentDirections.actionUserInfoFragmentToCancelMenbershipFragment(userInfo))
        }
    }
}