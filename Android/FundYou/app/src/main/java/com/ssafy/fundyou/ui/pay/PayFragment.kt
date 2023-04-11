package com.ssafy.fundyou.ui.pay

import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentPayBinding
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.ui.pay.model.FundingPayModel
import java.text.DecimalFormat

class PayFragment : BaseFragment<FragmentPayBinding>(R.layout.fragment_pay) {
    private lateinit var payItem: FundingPayModel
    private var fundingPrice = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        initFundingItemView()
        countMessageLength()
        setFundingPrice()
        initFundingBtnListener()
        initLoadingPointBtnClickListener()
    }

    override fun initViewModels() {

    }

    private fun initFundingItemView() {
        //TODO(임시 데이터 추가)
        payItem =
            FundingPayModel("이수용", R.drawable.bg_banner_ssafylogo2, "삼성", "비스포크 냉장고", 100000, 60000, 80, 20000, 10000)

        binding.item = payItem
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

        editText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s?.length!! > 0){
                    if(s.toString().toInt() <= payItem.balance){
                        fundingPrice = editText.text.toString().toInt()
                        fundingBtn.text = "${DecimalFormat("#,##0").format(fundingPrice)}원 펀딩하기"
                    } else {
                        fundingPrice = payItem.balance
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
            fundingPrice = payItem.balance
            setTextFundingEditTextAndBtn(fundingPrice)
        }
        binding.btnPayFunding10000.setOnClickListener {
            fundingPrice = if (payItem.balance > fundingPrice + 10000) fundingPrice + 10000 else payItem.balance
            setTextFundingEditTextAndBtn(fundingPrice)

        }
        binding.btnPayFunding50000.setOnClickListener {
            fundingPrice = if (payItem.balance > fundingPrice + 50000) fundingPrice + 50000 else payItem.balance
            setTextFundingEditTextAndBtn(fundingPrice)
        }
    }

    private fun setTextFundingEditTextAndBtn(fundingPrice: Int){
        binding.btnPayFunding.text = "${DecimalFormat("#,##0").format(fundingPrice)}원 펀딩하기"
        binding.editPayFundingPrice.setText(fundingPrice.toString())
    }

    private fun initFundingBtnListener(){
        //TODO(임시 state 코드)
        val result = "fail"
        val payResult = ViewState.Success(result)
        binding.btnPayFunding.setOnClickListener {
            if(binding.editPayFundingPrice.text.isEmpty() || binding.editPayInputInfoSender.text.isEmpty()){
                Snackbar.make(requireView(), "필수 항목 입력을 확인해주세요", Snackbar.LENGTH_SHORT).show()
            } else {
                navigate(PayFragmentDirections.actionPayFragmentToPayResultFragment(payResult.value!!, payItem))
            }
        }
    }

    private fun initLoadingPointBtnClickListener(){
        binding.btnLayoutPayLoad.setOnClickListener {
            /** 잔여 포인트 임시 */
            navigate(PayFragmentDirections.actionPayFragmentToPointLoadFragment(10000))
        }
    }
}
