package com.ssafy.fundyou.ui.wishlist

import android.os.Bundle
import android.view.View
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentWishListBinding
import com.ssafy.fundyou.ui.base.BaseFragment

class WishListFragment : BaseFragment<FragmentWishListBinding>(R.layout.fragment_wish_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    override fun initView() {

    }

    override fun initViewModels() {
    }
}