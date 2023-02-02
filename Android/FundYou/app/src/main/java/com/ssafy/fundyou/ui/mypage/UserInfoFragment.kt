package com.ssafy.fundyou.ui.mypage

import android.os.Bundle
import android.view.View
import com.kakao.sdk.user.model.User
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentUserInfoBinding
import com.ssafy.fundyou.ui.base.BaseFragment

class UserInfoFragment : BaseFragment<FragmentUserInfoBinding>(R.layout.fragment_user_info) {
    private val userInfo = "김싸피"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        membershipWithdrawalClickListener()
    }

    override fun initViewModels() {
    }

    private fun membershipWithdrawalClickListener(){
        binding.tvUserinfoCancelMembershipTitle.setOnClickListener {
            //TODO(arg로 들어가는 데이터 수정)
            navigate(UserInfoFragmentDirections.actionUserInfoFragmentToCancelMenbershipFragment(userInfo))
        }
    }
}