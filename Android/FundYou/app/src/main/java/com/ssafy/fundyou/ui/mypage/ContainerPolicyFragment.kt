package com.ssafy.fundyou.ui.mypage

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentContainerPolicyBinding
import com.ssafy.fundyou.ui.common.BaseFragment

class ContainerPolicyFragment : BaseFragment<FragmentContainerPolicyBinding>(R.layout.fragment_container_policy) {
    private val policy : ContainerPolicyFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }
    override fun initView() {
        setContent()
    }

    override fun initViewModels() {
    }

    private fun setContent(){
        when(policy.content){
            "privacy" -> {
                binding.tvContainerPolicyContent.text = getString(R.string.privacy)
            }
            "service" -> {
                binding.tvContainerPolicyContent.text = getString(R.string.service)
            }
        }
    }

}