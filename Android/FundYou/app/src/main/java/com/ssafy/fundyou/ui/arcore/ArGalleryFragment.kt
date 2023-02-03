package com.ssafy.fundyou.ui.arcore

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentArGalleryBinding
import com.ssafy.fundyou.databinding.ItemArGalleryListBinding
import com.ssafy.fundyou.ui.arcore.adapter.ArGalleryAdapter
import com.ssafy.fundyou.ui.base.BaseFragment


class ArGalleryFragment : BaseFragment<FragmentArGalleryBinding>(R.layout.fragment_ar_gallery) {
    val storage = FirebaseStorage.getInstance("gs://fundyou-1674632553418.appspot.com/")
    val storageRef = storage.reference

    private val galleryAdapter: ArGalleryAdapter = ArGalleryAdapter().apply {
        addItemDownLoadEvent { url, binding -> test(url, binding) }
        addItemClickListener(object : ArGalleryAdapter.ItemClickListener{
            override fun onItemClicked() {
                navigate(ArGalleryFragmentDirections.actionArGalleryFragmentToArFragment())
            }
        })
    }
    private fun test(url :String, itemBinding: ItemArGalleryListBinding) {
        storageRef.child(url).downloadUrl.addOnSuccessListener {
            Log.d("suyong", "download success!!")
            Glide.with(requireContext()).load(it).into(itemBinding.image)
        }.addOnFailureListener {
            Log.d("suyong", "test: download failed!")
        }
        return
        binding.rcvGallery.adapter = galleryAdapter
    }

    override fun initView() {
        binding.apply {
            rcvGallery.apply {
                adapter = galleryAdapter
                layoutManager = GridLayoutManager(requireContext(), 2)
            }
        }
        galleryAdapter.submitList(listOf("0","sofa.jpg","sofa.jpg","sofa.jpg"))
    }

    override fun initViewModels() {}


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
}