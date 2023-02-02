package com.ssafy.fundyou.ui.arcore

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentArCaptureBinding
import com.ssafy.fundyou.ui.base.BaseFragment


class ArCaptureFragment : BaseFragment<FragmentArCaptureBinding>(R.layout.fragment_ar_capture) {
    private lateinit var bitmap: Bitmap

    override fun initView() {
        bitmap = getBitmapFromArgs()
    }

    override fun initViewModels() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        binding.apply {
            ivCapture.setImageBitmap(bitmap)
            btnSave.setOnClickListener {
                Toast.makeText(requireContext(), "저장되었습니다!", Toast.LENGTH_SHORT).show()
                navigate(ArCaptureFragmentDirections.actionArCaptureFragmentToArGalleryFragment())
            }
        }
    }

    private fun getBitmapFromArgs(): Bitmap {
        val args: ArCaptureFragmentArgs by navArgs()
        return args.arg1
    }
}