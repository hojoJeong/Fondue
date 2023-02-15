package com.ssafy.fundyou.ui.mypage

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentNotiSettingBinding
import com.ssafy.fundyou.ui.common.BaseFragment

class NotiSettingFragment : BaseFragment<FragmentNotiSettingBinding>(R.layout.fragment_noti_setting) {
    private val fcmViewModel by activityViewModels<FcmViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }
    override fun initView() {
        fcmViewModel.getNotificationStatus()
    }

    override fun initViewModels() {
        initNotiStatusObserve()
        initResultUpdateNotiSettingObserve()
    }

    private fun initNotiStatusObserve(){
        fcmViewModel.notiStatus.observe(viewLifecycleOwner){ response ->
            when(response){
                is ViewState.Loading -> {
                    Log.d(TAG, "initNotiStatusObserve: Get Notification Setting Status Loading...")
                }
                is ViewState.Success -> {
                    Log.d(TAG, "initNotiStatusObserve: ${response.value}")
                    when(response.value){
                        0 -> {
                            binding.swbtnNotiSetting.isChecked = false
                        }
                        1-> {
                            binding.swbtnNotiSetting.isChecked = true
                        }
                    }
                    initSwitchChangeListener()
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initResultUpdateNotiSettingObserve: Get Notification Setting Status Error...${response.message}")
                }
            }
        }
    }

    private fun initSwitchChangeListener(){
        binding.swbtnNotiSetting.setOnCheckedChangeListener { _, _ ->
            fcmViewModel.updateNotificationStatus()
        }
    }

    private fun initResultUpdateNotiSettingObserve(){
        fcmViewModel.resultUpdateNotiSetting.observe(viewLifecycleOwner){ response ->
            when(response){
                is ViewState.Loading -> {
                    Log.d(TAG, "initResultUpdateNotiSettingObserve: Update Notification Setting Status Loading...")
                }
                is ViewState.Success -> {
                    Log.d(TAG, "initResultUpdateNotiSettingObserve: Update Notification Setting Status Success...")
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initResultUpdateNotiSettingObserve: Update Notification Setting Status Error...${response.message}")
                }
            }
        }
    }

    companion object{
        private const val TAG = "NotiSettingFragment..."
    }
}