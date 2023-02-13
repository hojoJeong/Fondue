package com.ssafy.fundyou.ui.ar_capture_confirm

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentArCaptureBinding
import com.ssafy.fundyou.ui.common.BaseFragment
import com.ssafy.fundyou.util.getFormattedCurrentTime
import java.io.ByteArrayOutputStream


class ArCaptureFragment : BaseFragment<FragmentArCaptureBinding>(R.layout.fragment_ar_capture) {
    private lateinit var bitmap: Bitmap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        bitmap = getBitmapFromArgs()
        binding.apply {
            ivCapture.setImageBitmap(bitmap)
            btnSave.setOnClickListener {
                uploadFirebase()
            }
        }
    }

    private fun uploadFirebase() {
        FirebaseApp.initializeApp(requireContext())
        val storage = Firebase.storage

        // Create a storage reference from our app
        val storageRef = storage.reference

        // Create a reference to "mountains.jpg"
        val imgPath = getFormattedCurrentTime() + "suyong.jpg"
        val sofaRef = storageRef.child(imgPath)

        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val uploadTask = sofaRef.putBytes(data)
        uploadTask
        .addOnFailureListener {
            it.printStackTrace()
        }.addOnSuccessListener {
                Toast.makeText(requireContext(), "Image upload Success!!!", Toast.LENGTH_SHORT).show()
            navigate(ArCaptureFragmentDirections.actionArCaptureFragmentToArGalleryFragment())
        }.addOnProgressListener {
                binding.pgUpload.apply {
                    this.visibility = View.VISIBLE
                }
            }.addOnPausedListener {
                Log.d("suyong", "uploadFirebase: Paused")
            }
    }

    private fun getBitmapFromArgs(): Bitmap {
        val args: ArCaptureFragmentArgs by navArgs()
        return args.arg1
    }

    override fun initViewModels() {}
}