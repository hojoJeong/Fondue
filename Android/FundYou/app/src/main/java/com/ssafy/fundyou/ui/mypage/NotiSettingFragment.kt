package com.ssafy.fundyou.ui.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentNotiSettingBinding
import com.ssafy.fundyou.ui.base.BaseFragment

class NotiSettingFragment : BaseFragment<FragmentNotiSettingBinding>(R.layout.fragment_noti_setting) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    override fun initView() {
    }

    override fun initViewModels() {
    }

    private fun initSwtich(){
        binding.swbtnNotiSettingAll.setOnCheckedChangeListener { buttonView, isChecked ->  
            //TODO("스위치 클릭 처리")
        }
        
        binding.swbtnNotiSettingMessage.setOnCheckedChangeListener { buttonView, isChecked ->  }

        binding.swbtnNotiSettingFunding.setOnCheckedChangeListener { buttonView, isChecked ->  }
        
        binding.swbtnNotiSettingFundingClose.setOnCheckedChangeListener { buttonView, isChecked ->  }
        
        binding.swbtnNotiSettingFundingCloseDeadline.setOnCheckedChangeListener { buttonView, isChecked ->  }
    }

}