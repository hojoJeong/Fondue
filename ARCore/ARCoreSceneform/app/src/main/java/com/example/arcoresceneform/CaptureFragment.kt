package com.example.arcoresceneform

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.example.arcoresceneform.databinding.FragmentCaptureBinding


class CaptureFragment : Fragment() {
    private lateinit var binding: FragmentCaptureBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCaptureBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: CaptureFragmentArgs by navArgs()
        val bitmap = args.arg1
        binding.apply {
            ivCapture.setImageBitmap(bitmap)
            btnSave.setOnClickListener {
                Toast.makeText(requireContext(), "저장되었습니다!", Toast.LENGTH_SHORT).show()
                NavHostFragment.findNavController(this@CaptureFragment)
                    .navigate(CaptureFragmentDirections.actionCaptureFragmentToGalleryFragment())
            }
        }

    }
}