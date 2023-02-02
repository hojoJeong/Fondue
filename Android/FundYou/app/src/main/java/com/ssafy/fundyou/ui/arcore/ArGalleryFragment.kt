package com.ssafy.fundyou.ui.arcore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.ssafy.fundyou.databinding.FragmentArGalleryBinding
import com.ssafy.fundyou.ui.arcore.adapter.ArGalleryAdapter


class ArGalleryFragment : Fragment() {
    private lateinit var binding: FragmentArGalleryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentArGalleryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val galleryAdapter = ArGalleryAdapter(listOf(1, 2, 3, 4, 5))
        binding.apply {
            rcvGallery.adapter = galleryAdapter
            galleryAdapter.itemClickListener = object : ArGalleryAdapter.ItemClickListener {
                override fun onItemClicked() {
                    NavHostFragment.findNavController(this@ArGalleryFragment)
                        .navigate(ArGalleryFragmentDirections.actionArGalleryFragmentToArFragment())

                }

            }
        }

    }

}