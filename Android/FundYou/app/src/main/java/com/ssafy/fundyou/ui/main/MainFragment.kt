package com.ssafy.fundyou.ui.main

import android.os.Bundle
import android.view.View
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentMainBinding
import com.ssafy.fundyou.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        binding.test.setOnClickListener {
            navigate(MainFragmentDirections.actionMainFragmentToSearchFragment())
        }
    }

    override fun initViewModels() {

    }
}