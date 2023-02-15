package com.ssafy.fundyou.ui.pay

import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentPayBinding
import com.ssafy.fundyou.ui.common.BaseFragment
import com.ssafy.fundyou.ui.pay.model.FundingPayItemUiModel
import com.ssafy.fundyou.ui.point.PointViewModel
import com.ssafy.fundyou.util.extension.addComma
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat

@AndroidEntryPoint
class PayFragment : BaseFragment<FragmentPayBinding>(R.layout.fragment_pay) {

    private var fundingPrice = 0
    private lateinit var payItemUiModel: FundingPayItemUiModel
    private var userBalance = 0

    private val payViewModel by activityViewModels<PayViewModel>()
    private val pointViewModel by activityViewModels<PointViewModel>()
    private val argument by navArgs<PayFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        payViewModel.getFundingItem(argument.fundingItemId)
        payViewModel.setFundingItemId(argument.fundingItemId)
        Log.d(TAG, "initView: ${payViewModel.fundingItemId.value}")
    }

    override fun initViewModels() {
        initFundingPayItemObserver()
        initUserPointObserver()
        initFundingPayObserver()
    }

    private fun initFundingPayItemObserver() {
        payViewModel.fundingItem.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    payItemUiModel = response.value!!
                    binding.item = payItemUiModel
                    payViewModel.getPayUserInfo()
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initFundingPayItemObserver: ${response.message}")
                }
            }
        }
    }

    private fun initUserPointObserver() {
        payViewModel.userInfo.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    binding.user = response.value!!
                    userBalance = response.value!!.userBalance
                    initFundingItemView()
                    countMessageLength()
                    setFundingPrice()
                    initFundingBtnListener()
                    initLoadingPointBtnClickListener()
                }
                is ViewState.Error -> {

                }
            }
        }
    }

    private fun initFundingItemView() {
        val content = "남은 금액 전부 펀딩"
        val builder = SpannableStringBuilder(content)
        builder.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.franch_rose)),
            content.length - 5,
            content.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.tvPayFundingAll.text = builder
    }

    private fun countMessageLength() {
        binding.editPayInputInfoMessage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val textCount = s?.length ?: 0
                binding.tvPayInputInfoMessageIndicator.text = "$textCount / 50"
            }

            override fun afterTextChanged(s: Editable?) {}
        })

    }

    private fun setFundingPrice() {
        val editText = binding.editPayFundingPrice
        val fundingBtn = binding.btnPayFunding

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length!! > 0) {
                    if (s.toString().toInt() <= payItemUiModel.fundingBalancePrice) {
                        fundingPrice = editText.text.toString().toInt()
                        fundingBtn.text = "${DecimalFormat("#,##0").format(fundingPrice)}원 펀딩하기"
                    } else {
                        fundingPrice = payItemUiModel.fundingBalancePrice
                        setTextFundingEditTextAndBtn(fundingPrice)
                    }
                } else {
                    fundingPrice = 0
                    fundingBtn.text = "펀딩하기"
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnPayFundingAll.setOnClickListener {
            fundingPrice = payItemUiModel.fundingBalancePrice
            setTextFundingEditTextAndBtn(fundingPrice)
        }
        binding.btnPayFunding10000.setOnClickListener {
            fundingPrice =
                if (payItemUiModel.fundingBalancePrice > fundingPrice + 10000) fundingPrice + 10000 else payItemUiModel.fundingBalancePrice
            setTextFundingEditTextAndBtn(fundingPrice)

        }
        binding.btnPayFunding50000.setOnClickListener {
            fundingPrice =
                if (payItemUiModel.fundingBalancePrice > fundingPrice + 50000) fundingPrice + 50000 else payItemUiModel.fundingBalancePrice
            setTextFundingEditTextAndBtn(fundingPrice)
        }
    }

    private fun setTextFundingEditTextAndBtn(fundingPrice: Int) {
        binding.btnPayFunding.text = "${DecimalFormat("#,##0").format(fundingPrice)}원 펀딩하기"
        binding.editPayFundingPrice.setText(fundingPrice.toString())
    }

    private fun initFundingPayObserver() {
        payViewModel.attendFundingItem.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initFundingPayObserver: loading...")
                }
                is ViewState.Success -> {
                    navigate(
                        PayFragmentDirections.actionPayFragmentToPayResultFragment(
                            response.value!!,
                            addComma(fundingPrice)
                        )
                    )
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initFundingPayObserver: error... ${response.message}")
                    if (response.value?.message == "잔액이 부족합니다.") {
                        navigate(PayFragmentDirections.actionPayFragmentToPayResultFragment(response.value!!, addComma(fundingPrice)))
                    }
                }
            }
        }
    }

    private fun initFundingBtnListener() {
        binding.btnPayFunding.setOnClickListener {
            if (binding.editPayFundingPrice.text.isEmpty() || binding.editPayInputInfoSender.text.isEmpty()) {
                Snackbar.make(requireView(), "필수 항목 입력을 확인해주세요", Snackbar.LENGTH_SHORT).show()
            } else {
                payViewModel.attendFundingItem(
                    payItemUiModel.fundingItemId,
                    binding.editPayInputInfoMessage.text.toString(),
                    fundingPrice
                )
            }
        }
    }

    private fun initLoadingPointBtnClickListener() {
        binding.btnLayoutPayLoad.setOnClickListener {
            pointViewModel.setBeforeFragment("pay")
            navigate(PayFragmentDirections.actionPayFragmentToPointLoadFragment(userBalance))
        }
    }

    companion object {
        private const val TAG = "PayFragment..."
    }
}
