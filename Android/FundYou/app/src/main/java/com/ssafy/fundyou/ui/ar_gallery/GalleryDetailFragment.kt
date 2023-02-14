package com.ssafy.fundyou.ui.ar_gallery

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentGalleryDetailBinding
import com.ssafy.fundyou.ui.common.BaseFragment

class GalleryDetailFragment : BaseFragment<FragmentGalleryDetailBinding>(R.layout.fragment_gallery_detail) {

    private val args: GalleryDetailFragmentArgs by navArgs()

    override fun initView() {
        val url = (args.url)
        Glide.with(requireContext()).load(url).into(binding.imgArDetail)
    }

    override fun initViewModels() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
}