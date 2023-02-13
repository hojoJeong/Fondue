package com.ssafy.fundyou.ui.mypage

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentMenbershipWithdrawalBinding
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.ui.login.LoginActivity
import com.ssafy.fundyou.util.showSnackBar

class MembershipWithdrawalFragment :
    BaseFragment<FragmentMenbershipWithdrawalBinding>(R.layout.fragment_menbership_withdrawal) {
    private val userInfo: MembershipWithdrawalFragmentArgs by navArgs()
    private val myPageViewModel by activityViewModels<MyPageViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModels()
    }

    override fun initView() {
        binding.tvCancelMembershipName.text =
            getString(R.string.content_cancel_membership_username, userInfo.userInfo.userName)
        initWithDrawalMenu()
        backBtnClickListener()
        initWithdrawalBtnClickListener()
    }

    override fun initViewModels() {
        initResultWithdrawalMemberShipObserve()
    }

    private fun initWithDrawalMenu() {
        val menuArray = resources.getStringArray(R.array.menu_withdrawal_membership_reason)
        val menuAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, menuArray)
        val menu = binding.menuCancelMembershipReasonItem
        menu.setAdapter(menuAdapter)

        menu.setOnItemClickListener { _, _, position, _ ->
            menu.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            with(binding.btnCancelMembershipWithdrawal) {
                isEnabled = true
                background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.bg_rect_grenadier_round5_stroke0
                )
                setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }

            when (position + 1) {
                3 -> {
                    showSolution(3)
                    moveBtnClickListener("mainFragment")
                }
                4 -> {
                    showSolution(4)
                    moveBtnClickListener("notiSettingFragment")
                }
                else -> {
                    binding.cstlCancelMembershipSolution.visibility = View.GONE
                }
            }
        }
    }

    private fun moveBtnClickListener(fragment: String) {
        binding.tvCancelMembershipSolutionBtn.setOnClickListener {
            when (fragment) {
                "mainFragment" -> {
                    navigate(MembershipWithdrawalFragmentDirections.actionCancelMenbershipFragmentToMainFragment())
                }
                "notiSettingFragment" -> {
                    navigate(MembershipWithdrawalFragmentDirections.actionCancelMenbershipFragmentToNotiSettingFragment())
                }
            }
        }
    }

    private fun showSolution(position: Int) {
        when (position) {
            3 -> {
                with(binding) {
                    cstlCancelMembershipSolution.visibility = View.VISIBLE
                    tvCancelMembershipSolutionTitle.text =
                        getString(R.string.content_cancel_membership_solution_3_title)
                    tvCancelMembershipSolutionContent.text =
                        getString(R.string.content_cancel_membership_solution_3_content)
                    tvCancelMembershipSolutionBtn.text =
                        getString(R.string.content_cancel_membership_solution_btn, "홈으로")
                }
            }
            4 -> {
                with(binding) {
                    binding.cstlCancelMembershipSolution.visibility = View.VISIBLE
                    tvCancelMembershipSolutionTitle.text =
                        getString(R.string.content_cancel_membership_solution_4_title)
                    tvCancelMembershipSolutionContent.text =
                        getString(R.string.content_cancel_membership_solution_4_content)
                    tvCancelMembershipSolutionBtn.text =
                        getString(R.string.content_cancel_membership_solution_btn, "알림 설정으로")
                }
            }
        }
        binding.tvCancelMembershipSolutionBtn.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

    private fun backBtnClickListener(){
        binding.btnCancelMembershipBack.setOnClickListener {
            popBackStack()
        }
    }

    private fun initWithdrawalBtnClickListener(){
        binding.btnCancelMembershipWithdrawal.setOnClickListener {
            myPageViewModel.withdrawalMembership()
        }
    }

    private fun initResultWithdrawalMemberShipObserve(){
        myPageViewModel.resultWithdrawal.observe(viewLifecycleOwner){ response ->
            when(response){
                is ViewState.Loading -> {
                    Log.d(TAG, "initResultWithdrawalMemberShip: Withdrawal Membership Loading...")
                }
                is ViewState.Success -> {
                    myPageViewModel.clearAuthPreference()
                    val intent = Intent(requireContext(), LoginActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    startActivity(intent)
                }
                is ViewState.Error -> {
                    requireView().showSnackBar("회원탈퇴에 실패했습니다. 잠시 후에 다시 시도해주세요.")
                    Log.d(TAG, "initResultWithdrawalMemberShip: Withdrawal Membership Error...${response.message}")
                }
            }
        }
    }
}