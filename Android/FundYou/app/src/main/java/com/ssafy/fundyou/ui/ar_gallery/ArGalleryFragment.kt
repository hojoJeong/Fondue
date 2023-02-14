package com.ssafy.fundyou.ui.ar_gallery

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentArGalleryBinding
import com.ssafy.fundyou.databinding.ItemArGalleryListBinding
import com.ssafy.fundyou.ui.ar_gallery.adapter.ArGalleryAdapter
import com.ssafy.fundyou.ui.ar_gallery.model.ArImageUIModel
import com.ssafy.fundyou.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArGalleryFragment : BaseFragment<FragmentArGalleryBinding>(R.layout.fragment_ar_gallery) {
    private val storage = FirebaseStorage.getInstance("gs://fundyou-1674632553418.appspot.com/")
    private val storageRef = storage.reference
    private val arGalleryFragmentArgs: ArGalleryFragmentArgs by navArgs()
    private val arGalleryViewModel by viewModels<ArGalleryViewModel>()

    private val galleryAdapter: ArGalleryAdapter = ArGalleryAdapter().apply {
        addItemDownLoadEvent { url, binding -> test(url, binding) }
        addItemClickListener(object : ArGalleryAdapter.ItemClickListener{
            override fun onItemClicked(addMode: Boolean, url: String) {
                if(addMode){
                    navigate(ArGalleryFragmentDirections.actionArGalleryFragmentToArFragment(arGalleryFragmentArgs.fundingItemId))
                }else{
                    navigate(ArGalleryFragmentDirections.actionArGalleryFragmentToGalleryDetailFragment(url))
                }
            }
        })
    }
    private fun test(url :String, itemBinding: ItemArGalleryListBinding) {
        Log.d("TAG", "test: url")
        Glide.with(requireContext()).load(url).into(itemBinding.image)
    }

    override fun initView() {
        binding.apply {
            rcvGallery.apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = galleryAdapter
            }
        }
        arGalleryViewModel.getArImageList(arGalleryFragmentArgs.fundingItemId)
    }

    override fun initViewModels() {
        initArImageListObserver()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    private fun initArImageListObserver(){
        arGalleryViewModel.arImageList.observe(viewLifecycleOwner){ response ->
            when(response){
                is ViewState.Loading -> {
                    Log.d("suyong", "initArImageListObserver: loading...")
                }
                is ViewState.Success -> {
                    val result = response.value ?: emptyList()
                    result as MutableList
                    result.add(0, ArImageUIModel(0, ""))
                    Log.d("TAG", "initArImageListObserver: success!")
                    galleryAdapter.submitList(result)
                }
                is ViewState.Error -> {
                    Log.d("suyong", "initArImageListObserver: error.. ${response.message}")
                }
            }
        }
    }
}