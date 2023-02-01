package com.example.arcoresceneform

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import com.example.arcoresceneform.databinding.FragmentGalleryBinding


class GalleryFragment : Fragment() {
    private lateinit var binding: FragmentGalleryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGalleryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val galleryAdapter = GalleryAdapter(requireContext(), listOf(1,2,3,4,5))
        binding.apply {
            rcvGallery.adapter = galleryAdapter
            galleryAdapter.itemClickListener = object: GalleryAdapter.ItemClickListener{
                override fun onItemClicked() {
                    NavHostFragment.findNavController(this@GalleryFragment).navigate(R.id.action_galleryFragment_to_arFragment)

                }

            }
        }

    }

}