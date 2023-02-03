package com.ssafy.fundyou.ui.arcore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentArGalleryBinding
import com.ssafy.fundyou.ui.arcore.adapter.ArGalleryAdapter
import com.ssafy.fundyou.ui.base.BaseFragment


class ArGalleryFragment : BaseFragment<FragmentArGalleryBinding>(R.layout.fragment_ar_gallery) {

    private lateinit var galleryAdapter: ArGalleryAdapter
    override fun initView() {
        galleryAdapter = ArGalleryAdapter(listOf(1, 2, 3, 4, 5))
        binding.apply {
            rcvGallery.adapter = galleryAdapter
            galleryAdapter.itemClickListener = object : ArGalleryAdapter.ItemClickListener {
                override fun onItemClicked() {
                    navigate(ArGalleryFragmentDirections.actionArGalleryFragmentToArFragment())
                }
            }
        }
    }

    override fun initViewModels() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
}