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

    private val storage = FirebaseStorage.getInstance("gs://fundyou-1674632553418.appspot.com/")
    private val storageRef = storage.reference
    private val args: GalleryDetailFragmentArgs by navArgs()

    override fun initView() {
        val url = (args.arg1)
        storageRef.child(url).downloadUrl.addOnSuccessListener {
            Log.d("suyong", "download success!!")
            Glide.with(requireContext()).load(it).into(binding.imgArDetail)
        }.addOnFailureListener {
            Log.d("suyong", "test: download failed!")
        }
    }

    override fun initViewModels() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
}