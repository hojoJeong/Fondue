package com.ssafy.fundyou.ui.pay

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentPayResultBinding
import com.ssafy.fundyou.ui.base.BaseFragment

class PayResultFragment : BaseFragment<FragmentPayResultBinding>(R.layout.fragment_pay_result) {
    private val payResult : PayResultFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    override fun initView() {
        checkState()
    }

    override fun initViewModels() {
    }

    private fun checkState(){
        when(payResult.state){
            "success" -> {
                initSuccessView()
            }
            "fail" -> {
                initFailView()
            }
        }
    }

    private fun initSuccessView(){
        with(binding){
            item = payResult.payinfo
            btnPayResultBtn.setOnClickListener {
                navigate(PayResultFragmentDirections.actionPayResultFragmentToInvitedFondueFragment())
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(this){
                navigate(PayResultFragmentDirections.actionPayResultFragmentToInvitedFondueFragment())
        }
    }

    private fun initFailView(){
        with(binding){
            tvPayResultTitle.visibility = View.INVISIBLE
            cstlPayResult.visibility = View.GONE
            tvPayResultBrand.textSize = 20f
            tvPayResultBrand.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            tvPayResultName.textSize = 16f
            tvPayResultName.setTextColor(ContextCompat.getColor(requireContext(), R.color.nobel))

            btnPayResultBtn.text = "다시 선물하기"

            btnPayResultBtn.setOnClickListener {
                popBackStack()
            }
        }
    }
}