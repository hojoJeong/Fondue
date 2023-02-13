package com.ssafy.fundyou.ui.mypage

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentMenbershipWithdrawalBinding
import com.ssafy.fundyou.ui.common.BaseFragment

class MembershipWithdrawalFragment :
    BaseFragment<FragmentMenbershipWithdrawalBinding>(R.layout.fragment_menbership_withdrawal) {
    private val userInfo: MembershipWithdrawalFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    override fun initView() {
        binding.tvCancelMembershipName.text =
            getString(R.string.content_cancel_membership_username, userInfo.userInfo)
        initWithDrawalMenu()
        backBtnClickListener()
    }

    override fun initViewModels() {
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
}